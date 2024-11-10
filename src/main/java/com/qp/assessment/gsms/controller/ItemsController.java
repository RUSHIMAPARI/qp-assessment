package com.qp.assessment.gsms.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qp.assessment.gsms.request.ActivationObject;
import com.qp.assessment.gsms.request.InventoryUpdateRequest;
import com.qp.assessment.gsms.request.ItemsRequest;
import com.qp.assessment.gsms.request.UpdateItemRequest;
import com.qp.assessment.gsms.response.MessageResponse;
import com.qp.assessment.gsms.service.IGroceryService;
import com.qp.assessment.gsms.util.CommonConstants.Pagination;

@RestController
@RequestMapping("/api/items")
public class ItemsController {
	
	@Autowired
	private IGroceryService groceryService;
	
//	@Autowired
//	private JwtTokenUtil jwtTokenUtil;
//	
//	@Autowired
//	private AuthService authService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	public MessageResponse addItems(@RequestHeader(name = "Authorization") String accessToken, @Valid @RequestBody List<ItemsRequest> itemsRequest) {
//		User user = authService.findByUserName(jwtTokenUtil.getUseridFromToken(accessToken));
		groceryService.saveItems(itemsRequest);
		return MessageResponse.success();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/page")
	public MessageResponse getAllItems(@RequestHeader(name = "Authorization") String accessToken,
			@PageableDefault(page = Pagination.DEFAULT_PAGE_NUMBER, size = Pagination.DEFAULT_PAGE_SIZE) @SortDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable,
			@RequestParam Optional<String> filterText) {
//		User user = authService.findByUserName(jwtTokenUtil.getUseridFromToken(accessToken));
		return groceryService.findAllItemsFilteredPageable(pageable, filterText);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{key}")
	public MessageResponse patch(@RequestHeader(name = "Authorization") String accessToken, @PathVariable Long key, @RequestBody ActivationObject obj) {
//		User user = authService.findByUserName(jwtTokenUtil.getUseridFromToken(accessToken));
		groceryService.toggleActivationItem(key, obj.getActiveFlag());
		return MessageResponse.success();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/update/{key}")
	public MessageResponse updateItems(@RequestHeader(name = "Authorization") String accessToken, @PathVariable Long key, @RequestBody UpdateItemRequest updateItemRequest) {
//		User user = authService.findByUserName(jwtTokenUtil.getUseridFromToken(accessToken));
		groceryService.updateItem(key, updateItemRequest);
		return MessageResponse.success();
	}
	

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/{key}/inventory")
    public MessageResponse updateInventory(@RequestHeader(name = "Authorization") String accessToken, @PathVariable Long key, @RequestBody InventoryUpdateRequest inventoryUpdateRequest) {
        groceryService.updateInventory(key, inventoryUpdateRequest);
        return MessageResponse.success();
    }

}
