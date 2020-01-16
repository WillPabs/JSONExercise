package com.fdmgroup.main;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Shield {
	
	String name;
	String shape;
	String type;
	
	@JsonCreator
	public Shield() {
		
	}
	
	@JsonCreator
	public Shield(@JsonProperty("name")String name, @JsonProperty("shape")String shape, @JsonProperty("type")String type) {
		super();
		this.name = name;
		this.shape = shape;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getShape() {
		return shape;
	}
	
	public void setShape(String shape) {
		this.shape = shape;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Shield [name=" + name + ", shape=" + shape + ", type=" + type + "]";
	}
	
	

}
