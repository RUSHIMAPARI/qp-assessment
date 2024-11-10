package com.qp.assessment.gsms.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qp.assessment.gsms.config.JwtTokenUtil;
import com.qp.assessment.gsms.entity.User;
import com.qp.assessment.gsms.request.OrderRequest;
import com.qp.assessment.gsms.response.MessageResponse;
import com.qp.assessment.gsms.service.AuthService;
import com.qp.assessment.gsms.service.IUserService;
import com.qp.assessment.gsms.util.CommonConstants.Pagination;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthService authService;
	
//	- View the list of available grocery items
	@PreAuthorize("hasRole('USER')")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("")
	public MessageResponse getAllItems(@RequestHeader(name = "Authorization") String accessToken,
			@PageableDefault(page = Pagination.DEFAULT_PAGE_NUMBER, size = Pagination.DEFAULT_PAGE_SIZE) @SortDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable,
			@RequestParam Optional<String> filterText) {
//		User user = authService.findByUserName(jwtTokenUtil.getUseridFromToken(accessToken));
		return userService.findAllItemsFilteredPageable(pageable, filterText);
	}

	@PreAuthorize("hasRole('USER')")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/order")
	public MessageResponse placeOrder(@RequestHeader(name = "Authorization") String accessToken, @Valid @RequestBody OrderRequest orderRequest) {
		User user = authService.findByUserName(jwtTokenUtil.getUseridFromToken(accessToken));
		userService.placeOrder(orderRequest, user);
		return MessageResponse.success();
	}

}
