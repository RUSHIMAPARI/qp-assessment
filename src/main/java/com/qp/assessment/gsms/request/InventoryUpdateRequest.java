package com.qp.assessment.gsms.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryUpdateRequest implements Serializable {

	private static final long serialVersionUID = 1311731834744309845L;
	
	private int quantity;
	
}
