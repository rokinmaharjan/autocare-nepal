package com.policynepal.autocare.vehicle.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.policynepal.autocare.common.dto.PageDto;
import com.policynepal.autocare.exceptionhandler.GlobalException;
import com.policynepal.autocare.vehicle.dto.SearchVehiclesDto;
import com.policynepal.autocare.vehicle.dto.VehicleDto;
import com.policynepal.autocare.vehicle.model.Vehicle;
import com.policynepal.autocare.vehicle.model.VehicleImage;
import com.policynepal.autocare.vehicle.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@PostMapping
	public Vehicle addVehicle(@RequestBody VehicleDto vehicleDto) {
		return vehicleService.addVehicle(vehicleDto);
	}

	@GetMapping
	public PageDto getVehiclesWithPaging(@RequestParam(required = true) Integer page,
			@RequestParam(required = true) Integer size) {
		return vehicleService.findVehiclesWithPaging(page, size);
	}

	@PutMapping("/{id}")
	public Vehicle updateVehicle(@PathVariable("id") String vehicleId, @RequestBody VehicleDto vehicleDto) throws GlobalException {
		return vehicleService.updateVehicle(vehicleId, vehicleDto);
	}

	@PostMapping("/search")
	public PageDto searchVehiclesWithFilterAndPaging(@RequestParam(required = true) Integer page,
			@RequestParam(required = true) Integer size,
			@RequestBody SearchVehiclesDto searchVehiclesDto) {
		return vehicleService.searchVehiclesWithFilterAndPaging(searchVehiclesDto, page, size);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteVehicle(@PathVariable("id") String vehicleId) throws GlobalException {
		vehicleService.deleteVehicle(vehicleId);
		
		HashMap<String, Boolean> response = new HashMap<>();
		response.put("success", true);
		
		return response;
	}
	
	@PostMapping("/{id}/images")
	public VehicleImage uploadImage(@PathVariable("id") String vehicleId, @RequestParam("image") MultipartFile image) throws GlobalException {
		return vehicleService.uploadImage(vehicleId, image);
	}
	
	@DeleteMapping("/{id}/images/{imageId}")
	public Map<String, Boolean> deleteImage(@PathVariable("id") String vehicleId, @PathVariable("imageId") String imageId) throws GlobalException {
		vehicleService.deleteVehicleImage(vehicleId, imageId);
		
		HashMap<String, Boolean> response = new HashMap<>();
		response.put("success", true);
		
		return response;
	}

}
