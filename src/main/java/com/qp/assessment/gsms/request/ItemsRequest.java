package com.qp.assessment.gsms.request;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.qp.assessment.gsms.entity.Items;
import com.qp.assessment.gsms.util.CommonConstants.ITEMS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemsRequest implements Serializable, RequestPayload<Items>{
	private static final long serialVersionUID = 191906027052762922L;
	
	@NotBlank(message = ITEMS.VALIDATION.NAME_REQUIRED)
	private String name;

	@NotBlank(message = ITEMS.VALIDATION.DESCRIPTION_REQUIRED)
	private String description;
	
	@NotBlank(message = ITEMS.VALIDATION.PRICE_REQUIRED)
	private BigDecimal price;

	@NotEmpty(message = ITEMS.VALIDATION.QUANTITY_REQUIRED)
	@Size(min = 1, max = 100, message = ITEMS.VALIDATION.QUANTITY_BETWEENLENGTH)
	private int quantity;
	
	@Override
	public Items mapToEntity() {
		Items items = new Items();
		BeanUtils.copyProperties(this, items);
		return items;
	}
	
	@Override
	public void updateEntity(Items items) {
		BeanUtils.copyProperties(this, items);
	}
	
}

