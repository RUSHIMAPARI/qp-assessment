package com.qp.assessment.gsms.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.qp.assessment.gsms.util.CommonConstants.ITEMS;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ActivationObject implements Serializable{

	private static final long serialVersionUID = 172627685943375591L;
	
	@NotBlank(message = ITEMS.VALIDATION.FLAG_REQUIRED)
	private Character activeFlag;
}
