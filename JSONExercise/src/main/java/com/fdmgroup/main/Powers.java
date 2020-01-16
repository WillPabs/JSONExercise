package com.fdmgroup.main;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Powers {
	
	private String name;
	private int powerLevel;
	
	@JsonCreator
	public Powers() {
		
	}
	

	@JsonCreator
	public Powers(@JsonProperty("name")String name, @JsonProperty("powerLevel")int powerLevel) {
		super();
		this.name = name;
		this.powerLevel = powerLevel;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPowerLevel() {
		return powerLevel;
	}
	
	public void setPowerLevel(int powerLevel) {
		this.powerLevel = powerLevel;
	}
	
	@Override
	public String toString() {
		return "Powers [name=" + name + ", powerLevel=" + powerLevel + "]";
	}
	

}
