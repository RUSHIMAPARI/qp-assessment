package com.qp.assessment.gsms.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Items extends AbstractEntity {
	private static final long serialVersionUID = -6940633033174604979L;

	@Id
	@Column(name = "key", nullable = false)
	@SequenceGenerator(name = "items_seq", sequenceName = "items_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "items_seq")
	@ToString.Include
	private Long key;
	
	private String name;
	
    private String description;
    
    private BigDecimal price;
    
    private int quantity;
	
	
}
