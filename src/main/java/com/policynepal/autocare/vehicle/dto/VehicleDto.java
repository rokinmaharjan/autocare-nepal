package com.policynepal.autocare.vehicle.dto;

import java.util.List;

public class VehicleDto {
	private String brand;
	private String model;
	private Boolean twoWheeler;
	private Boolean newVehicle;
	private Double distanceDriven;
	private Double price;
	private Boolean sold;
	private List<String> images;
	private String description;
	private Float usedMonths;
	private Boolean priceNegotiable;
	private Float mileage;
	private List<String> tags;
	private Double[] location;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Boolean getTwoWheeler() {
		return twoWheeler;
	}

	public void setTwoWheeler(Boolean twoWheeler) {
		this.twoWheeler = twoWheeler;
	}

	public Boolean getNewVehicle() {
		return newVehicle;
	}

	public void setNewVehicle(Boolean newVehicle) {
		this.newVehicle = newVehicle;
	}

	public Double getDistanceDriven() {
		return distanceDriven;
	}

	public void setDistanceDriven(Double distanceDriven) {
		this.distanceDriven = distanceDriven;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getSold() {
		return sold;
	}

	public void setSold(Boolean sold) {
		this.sold = sold;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getUsedMonths() {
		return usedMonths;
	}

	public void setUsedMonths(Float usedMonths) {
		this.usedMonths = usedMonths;
	}

	public Boolean getPriceNegotiable() {
		return priceNegotiable;
	}

	public void setPriceNegotiable(Boolean priceNegotiable) {
		this.priceNegotiable = priceNegotiable;
	}

	public Float getMileage() {
		return mileage;
	}

	public void setMileage(Float mileage) {
		this.mileage = mileage;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Double[] getLocation() {
		return location;
	}

	public void setLocation(Double[] location) {
		this.location = location;
	}

}
