package com.policynepal.autocare.vehicle.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.policynepal.autocare.vehicle.dto.SearchVehiclesDto;
import com.policynepal.autocare.vehicle.model.Vehicle;

public interface CustomVehicleRepository {
	Page<Vehicle> searchVehiclesWithFilterAndPaging(SearchVehiclesDto searchVehiclesDto, Pageable pageable);
}
