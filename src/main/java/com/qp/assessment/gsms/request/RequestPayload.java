package com.qp.assessment.gsms.request;

import java.io.Serializable;

public interface RequestPayload<T> extends Serializable {

	public T mapToEntity();

	public void updateEntity(T t);
}
