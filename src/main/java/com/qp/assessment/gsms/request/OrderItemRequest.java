package com.qp.assessment.gsms.request;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.qp.assessment.gsms.util.CommonConstants.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @NotNull(message = ValidationMessages.ITEM_ID_REQUIRED)
    private Long itemId;

    @Min(value = 1, message = ValidationMessages.QUANTITY_MINIMUM)
    private int quantity;
	
}