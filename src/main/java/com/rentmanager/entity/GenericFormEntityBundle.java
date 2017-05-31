package com.rentmanager.entity;

import java.util.List;

/**
 * @author : tanmay
 * @created : 31-May-2017
 */
public class GenericFormEntityBundle {
	private Integer size;
	private List<GenericFormEntity> stack;

	public List<GenericFormEntity> getStack() {
		return stack;
	}

	public void setStack(List<GenericFormEntity> stack) {
		this.stack = stack;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
