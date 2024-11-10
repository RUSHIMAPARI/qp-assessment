package com.qp.assessment.gsms.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RoleTypes {
	
	ADMIN,
	USER;
	
	public static List<RoleTypes> findLike(String roleTypeStr) {
		return Arrays.asList(RoleTypes.values()).stream()
		.filter(r -> r.name().indexOf(roleTypeStr) >= 0)
		.collect(Collectors.toList());
	}
	
}
