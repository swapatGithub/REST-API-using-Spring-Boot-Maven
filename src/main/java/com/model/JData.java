package com.model;

import org.springframework.hateoas.ResourceSupport;

public class JData extends ResourceSupport{
	
	private Gene[] data;
	
	public void setData(Gene[] data) {
		this.data = data;
	}
	
	public Gene[] getData() {
		return data;
	}
	
}