package com.qp.assessment.gsms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItems extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "key", nullable = false)
	@SequenceGenerator(name = "order_items_seq", sequenceName = "order_items_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "order_items_seq")
	private Long key;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", referencedColumnName = "key")
    private Orders order;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id", referencedColumnName = "key")
    private Items item;

    private int quantity;

	public static OrderItems init(Items item) {
		return OrderItems.builder()
				.item(item)
				.build();
	}
	
	
}
