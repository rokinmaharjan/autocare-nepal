package com.policynepal.autocare.vehicle.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Document(collection = "vehicles")
public class Vehicle {
	@Id
	private String id;
	private String userId;
	private String brand;
	private String model;
	private Boolean twoWheeler = false;
	private Boolean newVehicle = true;
	private Double distanceDriven;
	private Double price;
	private Boolean sold = false;
	private List<VehicleImage> images;
	private String description;
	private Float usedMonths;
	private Boolean priceNegotiable = false;
	private Float mileage;
	private List<String> tags;
	private Double[] location;
	@JsonProperty(access = Access.READ_ONLY)
	@CreatedDate
	private Date createdDate;
	@JsonProperty(access = Access.READ_ONLY)
	@LastModifiedDate
	private Date modifiedDate;

	public Vehicle() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public List<VehicleImage> getImages() {
		return images;
	}

	public void setImages(List<VehicleImage> images) {
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public Double[] getLocation() {
		return location;
	}

	public void setLocation(Double[] location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", userId=" + userId + ", brand=" + brand + ", model=" + model + ", twoWheeler="
				+ twoWheeler + ", newVehicle=" + newVehicle + ", distanceDriven=" + distanceDriven + ", price=" + price
				+ ", sold=" + sold + ", images=" + images + ", description=" + description + ", usedMonths="
				+ usedMonths + ", priceNegotiable=" + priceNegotiable + ", mileage=" + mileage + ", tags=" + tags
				+ ", location=" + Arrays.toString(location) + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + "]";
	}

}
