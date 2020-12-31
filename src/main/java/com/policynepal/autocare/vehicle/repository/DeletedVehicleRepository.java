package com.policynepal.autocare.vehicle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.policynepal.autocare.vehicle.model.DeletedVehicle;

public interface DeletedVehicleRepository extends MongoRepository<DeletedVehicle, String> {

}
