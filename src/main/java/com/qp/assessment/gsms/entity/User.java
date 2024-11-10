package com.qp.assessment.gsms.entity;

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
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "key", nullable = false)
	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "user_seq")
	@ToString.Include
	private Long key;
	
	@Column(nullable = false, unique = true, length = 300)
	@ToString.Include
	private String userName;

	@ToString.Include
	private String firstName;
	
	@ToString.Include
	private String lastName;
	
	@Column(nullable = false, length = 500)
	private String password;
	
	private String mobileNumber;
	
	private String emailId;
	
	@Column(name = "role", nullable = false)
    private RoleTypes role;
	
	
}
