package com.qp.assessment.gsms.response;

import java.math.BigDecimal;

import com.qp.assessment.gsms.entity.Items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class ItemsResponseModel extends MessageResponse {
	private static final long serialVersionUID = -5173228930854399016L;

	private Long key;
	
	private String name;
	
    private String description;
    
    private BigDecimal price;
    
    private int quantity;
	
    private Character activeFlag;
	
	public static ItemsResponseModel init(Items items) {
		return ItemsResponseModel.builder()
				.key(items.getKey())
				.name(items.getName())
				.description(items.getDescription())
				.price(items.getPrice())
				.quantity(items.getQuantity())
				.activeFlag(items.getIsActive())
				.status(ServiceStatus.SUCCESS)
				.build();
	}
}
