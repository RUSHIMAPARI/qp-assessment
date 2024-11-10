package com.qp.assessment.gsms.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse implements Serializable {
	
	private static final long serialVersionUID = 5438693761340970650L;
	
	private ServiceStatus status;
	private String errorMessage;
	
	@JsonIgnore
	public boolean isError() {
		return this.status.equals(ServiceStatus.FAILURE);
	}
	
	@JsonIgnore
	public boolean isSuccess() {
		return this.status.equals(ServiceStatus.SUCCESS);
	}
	
	public static MessageResponse error(String errorMessage) {
		return MessageResponse.builder().status(ServiceStatus.FAILURE).errorMessage(errorMessage).build();
	}

	public static MessageResponse success() {
		return MessageResponse.builder().status(ServiceStatus.SUCCESS).build();
	}
	
	enum ServiceStatus {
		SUCCESS,
		FAILURE
	}
}
