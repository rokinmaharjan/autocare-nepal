package com.policynepal.autocare.vehicle.model;

public class VehicleImage {
	private String imageId;
	private String url;
	private String fileName;

	public VehicleImage() {
		super();
	}

	public VehicleImage(String imageId, String url, String fileName) {
		super();
		this.imageId = imageId;
		this.url = url;
		this.fileName = fileName;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
