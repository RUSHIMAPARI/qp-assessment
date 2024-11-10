package com.qp.assessment.gsms.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.qp.assessment.gsms.request.UserRequest;
import com.qp.assessment.gsms.response.MessageResponse;
import com.qp.assessment.gsms.response.UserPageWrapperResponse;
import com.qp.assessment.gsms.service.AuthService;
import com.qp.assessment.gsms.util.CommonConstants.Pagination;

@CrossOrigin
@RestController
@RequestMapping("/open")
public class AdminController {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthService authService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/save")
	public MessageResponse saveUser(@Valid @RequestBody UserRequest userRequest) {
		authService.saveUser(userRequest);
		return MessageResponse.success();
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/page")
	public UserPageWrapperResponse getAllUsers(@RequestHeader(name = "Authorization") String accessToken,
			@PageableDefault(page = Pagination.DEFAULT_PAGE_NUMBER, size = Pagination.DEFAULT_PAGE_SIZE) @SortDefault(sort = "userName", direction = Sort.Direction.ASC) Pageable pageable,
			@RequestParam Optional<String> filterText) {
		User user = authService.findByUserName(jwtTokenUtil.getUseridFromToken(accessToken));
		return authService.findAllUsersFilteredPageable(pageable, filterText, user);
	}

}
