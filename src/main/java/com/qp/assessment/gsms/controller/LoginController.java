package com.qp.assessment.gsms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qp.assessment.gsms.config.AuthenticatedUser;
import com.qp.assessment.gsms.config.JwtGeneratorAndValidator;
import com.qp.assessment.gsms.entity.User;
import com.qp.assessment.gsms.request.ValidatePasswordRequest;
import com.qp.assessment.gsms.response.JwtResponse;
import com.qp.assessment.gsms.service.AuthService;

@CrossOrigin
@RestController
@RequestMapping("/open")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtGeneratorAndValidator jwtTokenUtil;
	
	@Autowired
	private AuthService userService;
	
	@PostMapping("/login")
	public JwtResponse otpVerification(@RequestBody @Valid ValidatePasswordRequest request) {
		User user = userService.findByUserName(request.getUserName());
		if (user != null) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

			System.out.println("User name: " + request.getUserName() + " Password: " + request.getPassword());

			SecurityContextHolder.getContext().setAuthentication(authentication);

			AuthenticatedUser authUser = (AuthenticatedUser) authentication.getPrincipal();
			String accessToken = jwtTokenUtil.generateToken(authUser);

			return JwtResponse.success(accessToken, jwtTokenUtil.getExpiresInFromToken(accessToken),
					authUser.getUser().getRole(), authUser.getUser().getUserName());

		} else {
			System.out.println("Invalid user");
			return JwtResponse.error("Invalid user");
		}

	}

}
