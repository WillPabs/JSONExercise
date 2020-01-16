package com.fdmgroup.main;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fdmgroup.serialization.game.Backpack;
import com.fdmgroup.serialization.game.Player;


@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Wizard extends Player{

	private Shield shield;
	private Powers power;
	Backpack backpack;
	
	@JsonCreator
	public Wizard() {
		
	}
	@JsonCreator
	public Wizard(@JsonProperty("name")String name, @JsonProperty("healthpoints")int healthpoints, 
			@JsonProperty("shield")Shield shield, @JsonProperty("power")Powers power, @JsonProperty("backpack")Backpack backpack) {
		super();
		setName(name);
		setHealthPoints(healthpoints);
		this.shield = shield;
		this.power = power;
		this.backpack = backpack;
	}

	public Shield getShield() {
		return shield;
	}

	public void setShield(Shield shield) {
		this.shield = shield;
	}

	public Powers getPower() {
		return power;
	}

	public void setPower(Powers power) {
		this.power = power;
	}

	public Backpack getBackpack() {
		return backpack;
	}

	public void setBackpack(Backpack backpack) {
		this.backpack = backpack;
	}

	

	@Override
	public String toString() {
		return "Wizard [name=" + this.getName() + ", HealthPoints=" +this.getHealthPoints() + "shield=" + shield 
				+ ", power=" + power + ", backpack=" + backpack + "]";
	}
	
	
	
}
