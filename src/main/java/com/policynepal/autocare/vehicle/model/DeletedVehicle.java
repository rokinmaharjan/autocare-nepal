package com.policynepal.autocare.vehicle.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deletedVehicles")
public class DeletedVehicle {
	@Id
	private String id;
	private Vehicle vehicle;

	public DeletedVehicle() {
		super();
	}

	public DeletedVehicle(Vehicle vehicle) {
		super();
		this.vehicle = vehicle;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
