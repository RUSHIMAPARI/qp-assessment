package com.qp.assessment.gsms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.qp.assessment.gsms.entity.Items;
import com.qp.assessment.gsms.entity.OrderItems;
import com.qp.assessment.gsms.entity.Orders;
import com.qp.assessment.gsms.entity.User;
import com.qp.assessment.gsms.exception.FieldValidationException;
import com.qp.assessment.gsms.exception.ResourceNotFoundException;
import com.qp.assessment.gsms.repository.ItemsRepository;
import com.qp.assessment.gsms.repository.OrderRepository;
import com.qp.assessment.gsms.request.OrderItemRequest;
import com.qp.assessment.gsms.request.OrderRequest;
import com.qp.assessment.gsms.response.ItemsPageWrapperResponse;
import com.qp.assessment.gsms.response.ItemsResponseModel;
import com.qp.assessment.gsms.service.IUserService;
import com.qp.assessment.gsms.util.CommonConstants.ErrorConstants;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private ItemsRepository itemsRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public Items findByKey(Long key) {
		return itemsRepository.findByKeyAndIsActive(key, 'Y')
				.orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.RESOURCE_FOR_NOT_FOUND, key, "Item"));
	}

	@Override
	public ItemsPageWrapperResponse findAllItemsFilteredPageable(Pageable pageable, Optional<String> filterText) {
		Specification<Items> spec = null;

		spec = allFilteredSpec(filterText);
		
    	Page<ItemsResponseModel> page = itemsRepository.findAll(spec, pageable)
		.map(new Function<Items, ItemsResponseModel>() {
		    @Override
		    public ItemsResponseModel apply(Items items) {
		    	return ItemsResponseModel.init(items);
		    }
		});
    	
		return ItemsPageWrapperResponse.init(page);
	}
	
	private static Specification<Items> allFilteredSpec(Optional<String> filterText) {
		return new Specification<Items>() {
			private static final long serialVersionUID = 1L;
			public Predicate toPredicate(Root<Items> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<>();

				predicates.add(builder.equal(root.get("isActive"), 'Y'));
				
				if (filterText.isPresent()) {
					predicates.add(builder.like(builder.upper(root.get("name")), "%" + filterText.get().trim().toUpperCase() + "%"));
				}
				
                return builder.or(predicates.toArray(new Predicate[] {}));
            }
        };
	}

	@Override
	public void placeOrder(@Valid OrderRequest orderRequest, User user) {
		Orders order = Orders.builder()
			            .user(user)
			            .build();

	    List<OrderItems> orderItems = orderRequest.getItems().stream()
	            .map(orderItemDTO -> createOrderItem(orderItemDTO, order))
	            .collect(Collectors.toList());

	    order.setItems(orderItems);
	    orderRepository.save(order);
	}

	private OrderItems createOrderItem(OrderItemRequest orderItemRequest, Orders order) {
		Items item = itemsRepository.findByKeyAndIsActive(orderItemRequest.getItemId(), 'Y')
	            .orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.RESOURCE_FOR_NOT_FOUND, "", "Item"));

	    if (item.getQuantity() < orderItemRequest.getQuantity()) {
	        throw new FieldValidationException("Insufficient stock for item: " + item.getName());
	    }

	    // Deduct stock and update
	    item.setQuantity(item.getQuantity() - orderItemRequest.getQuantity());
	    itemsRepository.save(item);

	    // Build and return OrderItem
	    return OrderItems.builder()
	            .item(item)
	            .quantity(orderItemRequest.getQuantity())
	            .order(order)
	            .build();
	}
	
}
