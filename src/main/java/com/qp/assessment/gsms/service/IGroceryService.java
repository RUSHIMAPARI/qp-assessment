package com.qp.assessment.gsms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.qp.assessment.gsms.request.InventoryUpdateRequest;
import com.qp.assessment.gsms.request.ItemsRequest;
import com.qp.assessment.gsms.request.UpdateItemRequest;
import com.qp.assessment.gsms.response.ItemsPageWrapperResponse;

public interface IGroceryService {
	
	public void saveItems(List<ItemsRequest> itemsRequest);

	public ItemsPageWrapperResponse findAllItemsFilteredPageable(Pageable pageable, Optional<String> filterText);

	public void toggleActivationItem(Long key, Character activeFlag);

	public void updateItem(Long key, UpdateItemRequest updateItemRequest);

	public void updateInventory(Long key, InventoryUpdateRequest inventoryUpdateRequest);

}
