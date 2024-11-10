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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qp.assessment.gsms.entity.Items;
import com.qp.assessment.gsms.exception.ExchangeException;
import com.qp.assessment.gsms.exception.ResourceNotFoundException;
import com.qp.assessment.gsms.repository.ItemsRepository;
import com.qp.assessment.gsms.request.InventoryUpdateRequest;
import com.qp.assessment.gsms.request.ItemsRequest;
import com.qp.assessment.gsms.request.UpdateItemRequest;
import com.qp.assessment.gsms.response.ItemsPageWrapperResponse;
import com.qp.assessment.gsms.response.ItemsResponseModel;
import com.qp.assessment.gsms.service.IGroceryService;
import com.qp.assessment.gsms.util.CommonConstants.ErrorConstants;

@Service
public class GroceryService implements IGroceryService {
	
	@Autowired
	private ItemsRepository itemsRepository;
	
	public Items findByKey(Long key) {
		return itemsRepository.findByKeyAndIsActive(key, 'Y')
				.orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.RESOURCE_FOR_NOT_FOUND, "", "Item"));
	}

	@Override
	public void saveItems(List<ItemsRequest> itemsRequest) {
		
		if(CollectionUtils.isEmpty(itemsRequest)) {
			throw new ExchangeException("Items are required.");
		}
		
		List<Items> items = itemsRequest.stream()
			                .map(ItemsRequest::mapToEntity)
			                .collect(Collectors.toList());

		itemsRepository.saveAll(items);
	}

	@Override
	public ItemsPageWrapperResponse findAllItemsFilteredPageable(Pageable pageable, Optional<String> filterText) {
		Specification<Items> spec = null;

		if (filterText.isPresent()) {
			spec = allFilteredSpec(filterText);
		}
		
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

				predicates.add(builder.like(builder.upper(root.get("name")), "%" + filterText.get().trim().toUpperCase() + "%"));
				
                return builder.or(predicates.toArray(new Predicate[] {}));
            }
        };
	}

	@Override
	public void toggleActivationItem(Long key, Character activeFlag) {
		Items item = findByKey(key);
		item.setIsActive(activeFlag);
		itemsRepository.save(item);
	}

	@Override
	public void updateItem(Long key, UpdateItemRequest updateItemRequest) {
		Items item = findByKey(key);
		
		item.setName(updateItemRequest.getName());
		item.setPrice(updateItemRequest.getPrice());
		itemsRepository.save(item);
	}

	@Override
	public void updateInventory(Long key, InventoryUpdateRequest inventoryUpdateRequest) {
		Items item = findByKey(key);
		
		item.setQuantity(inventoryUpdateRequest.getQuantity());
		itemsRepository.save(item);
	}
	
}
