package com.policynepal.autocare.vehicle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.policynepal.autocare.vehicle.model.Vehicle;

public interface VehicleRepository extends MongoRepository<Vehicle, String>, CustomVehicleRepository {

}
