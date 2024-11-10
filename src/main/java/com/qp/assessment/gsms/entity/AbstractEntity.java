package com.qp.assessment.gsms.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Version
	private int version;
	
	@CreatedDate
	@Column(name = "CREATED_ON")
	private Instant createdOn;
	
	@CreatedBy
	@Column(name = "CREATED_BY")
	private Long createdBy;
	
	@LastModifiedDate
	@Column(name = "UPDATED_ON")
	private Instant updatedOn;
	
	@LastModifiedBy
	@Column(name = "UPDATED_BY")
	private Long updatedBy;
	
	@Column(name = "IS_ACTIVE")
	private Character isActive;
	
	@PrePersist
	public void onPrePersist() {
		this.isActive = 'Y';
		this.createdOn = Instant.now();
	}
	
	@PreUpdate
	public void onPreUpdate() {
		this.updatedOn = Instant.now();
	}
	
	public AbstractEntity() {
		super();
	}
}
