package com.policynepal.autocare.vehicle.dto;

import java.util.List;

public class SearchVehiclesDto {
	private List<String> brands;
	private Float minPrice;
	private Float maxPrice;
	private List<String> condition;
	private LocationDetails locationDetails;

	public List<String> getBrands() {
		return brands;
	}

	public void setBrands(List<String> brands) {
		this.brands = brands;
	}

	public Float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}

	public Float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public List<String> getCondition() {
		return condition;
	}

	public void setCondition(List<String> condition) {
		this.condition = condition;
	}

	public LocationDetails getLocationDetails() {
		return locationDetails;
	}

	public void setLocationDetails(LocationDetails locationDetails) {
		this.locationDetails = locationDetails;
	}

	public static class LocationDetails {
		private Double[] location;
		private Float distance;

		public Double[] getLocation() {
			return location;
		}

		public void setLocation(Double[] location) {
			this.location = location;
		}

		public Float getDistance() {
			return distance;
		}

		public void setDistance(Float distance) {
			this.distance = distance;
		}

	}

}
