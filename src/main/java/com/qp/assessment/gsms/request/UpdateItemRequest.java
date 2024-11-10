package com.qp.assessment.gsms.request;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.qp.assessment.gsms.util.CommonConstants.ITEMS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateItemRequest implements Serializable{
	
	private static final long serialVersionUID = 2009447969351234096L;

	@NotBlank(message = ITEMS.VALIDATION.NAME_REQUIRED)
	private String name;

	@NotBlank(message = ITEMS.VALIDATION.PRICE_REQUIRED)
	private BigDecimal price;

}

