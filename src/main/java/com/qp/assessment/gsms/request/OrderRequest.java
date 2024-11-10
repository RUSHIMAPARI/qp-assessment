package com.qp.assessment.gsms.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.qp.assessment.gsms.util.CommonConstants.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest implements Serializable {
	
	private static final long serialVersionUID = 8761805959620163645L;

//    @NotNull(message = ValidationMessages.USER_ID_REQUIRED)
//    private Long userId;

    @NotEmpty(message = ValidationMessages.ITEMS_REQUIRED)
    private List<@NotNull OrderItemRequest> items;
	
}