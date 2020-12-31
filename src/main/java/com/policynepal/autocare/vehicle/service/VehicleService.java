package com.policynepal.autocare.vehicle.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.assertj.core.util.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.policynepal.autocare.aws.model.AwsCredential;
import com.policynepal.autocare.aws.service.S3Service;
import com.policynepal.autocare.common.dto.PageDto;
import com.policynepal.autocare.exceptionhandler.GlobalException;
import com.policynepal.autocare.utils.AuthenticationUtils;
import com.policynepal.autocare.utils.CustomBeanUtils;
import com.policynepal.autocare.vehicle.dto.SearchVehiclesDto;
import com.policynepal.autocare.vehicle.dto.VehicleDto;
import com.policynepal.autocare.vehicle.model.DeletedVehicle;
import com.policynepal.autocare.vehicle.model.Vehicle;
import com.policynepal.autocare.vehicle.model.VehicleImage;
import com.policynepal.autocare.vehicle.repository.DeletedVehicleRepository;
import com.policynepal.autocare.vehicle.repository.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	private AuthenticationUtils authUtils;

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private DeletedVehicleRepository deletedVehicleRepository;
	
	@Autowired
	private AmazonS3Client s3Client;
	
	@Autowired
	private AwsCredential awsCredential;
	
	private static final String IMAGE_KEY = "%s-%s";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);
	
	public Vehicle addVehicle(VehicleDto vehicleDto) {
		Vehicle vehicle = new Vehicle();

		CustomBeanUtils.copyNonNullProperties(vehicleDto, vehicle);

		vehicle.setUserId(authUtils.getUserId());

		return vehicleRepository.save(vehicle);
	}

	public PageDto findVehiclesWithPaging(Integer page, Integer size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<Vehicle> vehiclesPage = vehicleRepository.findAll(pageRequest);

		PageDto pageDto = new PageDto();
		pageDto.setContent(vehiclesPage.getContent());
		pageDto.setTotalElements(vehiclesPage.getTotalElements());
		pageDto.setTotalPages(vehiclesPage.getTotalPages());
		pageDto.setPageElementCount(vehiclesPage.getNumberOfElements());

		return pageDto;
	}

	public Vehicle updateVehicle(String vehicleId, VehicleDto vehicleDto) throws GlobalException {
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new GlobalException("Vehicle not found", HttpStatus.BAD_REQUEST));
		
		validateVehicleOwner(authUtils.getUserId(), vehicle);
		
		CustomBeanUtils.copyNonNullProperties(vehicleDto, vehicle);
		
		return vehicleRepository.save(vehicle);
	}
	
	private Vehicle validateVehicleOwner(String userId, Vehicle vehicle) throws GlobalException {
		if (userId.equals(vehicle.getUserId())) {
			return vehicle;
		}
		
		throw new GlobalException("Cannot update. Vehicle not owned by user.", HttpStatus.BAD_REQUEST);
	}

	public PageDto searchVehiclesWithFilterAndPaging(SearchVehiclesDto searchVehiclesDto, Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		
		Page<Vehicle> vehiclesPage = vehicleRepository.searchVehiclesWithFilterAndPaging(searchVehiclesDto, pageable);

		PageDto pageDto = new PageDto();
		pageDto.setContent(vehiclesPage.getContent());
		pageDto.setTotalElements(vehiclesPage.getTotalElements());
		pageDto.setTotalPages(vehiclesPage.getTotalPages());
		pageDto.setPageElementCount(vehiclesPage.getNumberOfElements());

		return pageDto;
	}

	public void deleteVehicle(String vehicleId) throws GlobalException {
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new GlobalException("Vehicle not found", HttpStatus.BAD_REQUEST));
		
		if(!authUtils.getUserId().equals(vehicle.getUserId())) {
			throw new GlobalException("Cannot delete vehicle added by other user", HttpStatus.BAD_REQUEST);
		}
		
		DeletedVehicle deletedVehicle = new DeletedVehicle(vehicle);
		deletedVehicleRepository.save(deletedVehicle);
		
		vehicleRepository.deleteById(vehicleId);
	}

	public VehicleImage uploadImage(String vehicleId, MultipartFile image) throws GlobalException {
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new GlobalException("Vehicle not found", HttpStatus.BAD_REQUEST));

		// Convert MultipartFile to File
		Path imagePath = Paths.get(image.getOriginalFilename());
		try {
			image.transferTo(imagePath);
		} catch (IllegalStateException | IOException e) {
			LOGGER.info("Exception occured while converting multipart file to file. Exception: {}", e.getMessage());
		}
		
		File imageFile = imagePath.toFile();
		
		String key = String.format(IMAGE_KEY, new Date().getTime(), imageFile.getName());
		
		S3Service.uploadFileInS3(s3Client, awsCredential.getS3().getBucketName(), key, imageFile);
		
		Files.delete(imageFile);
		LOGGER.info("Temp file '{}' deleted", key);
		
		String s3Url = S3Service.getFileUrl(s3Client, awsCredential.getS3().getBucketName(), key);
		
		VehicleImage vehicleImage = new VehicleImage(UUID.randomUUID().toString(), s3Url, key);
		
		List<VehicleImage> vehicleImages = vehicle.getImages();
		if (vehicleImages == null) {
			vehicleImages = new ArrayList<>();
		}
		vehicleImages.add(vehicleImage);
		vehicle.setImages(vehicleImages);
		
		vehicleRepository.save(vehicle);
		
		return vehicleImage;
	}

	public void deleteVehicleImage(String vehicleId, String imageId) throws GlobalException {
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new GlobalException("Vehicle not found", HttpStatus.BAD_REQUEST));
		
		List<VehicleImage> vehicleImages = new ArrayList<>();
		
		for (VehicleImage vehicleImage : vehicle.getImages()) {
			if (!imageId.equals(vehicleImage.getImageId())) {
				vehicleImages.add(vehicleImage);
			}
		}
		
		vehicle.setImages(vehicleImages);
		
		vehicleRepository.save(vehicle);
	}
	
}
