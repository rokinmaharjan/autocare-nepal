package com.policynepal.autocare.vehicle.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;

import com.policynepal.autocare.vehicle.dto.SearchVehiclesDto;
import com.policynepal.autocare.vehicle.dto.SearchVehiclesDto.LocationDetails;
import com.policynepal.autocare.vehicle.model.Vehicle;

public class CustomVehicleRepositoryImpl implements CustomVehicleRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	private static final String PRICE = "price";
	private static final String BRAND = "brand";
	private static final String NEW_VEHICLE = "newVehicle";

	@Override
	public Page<Vehicle> searchVehiclesWithFilterAndPaging(SearchVehiclesDto searchVehiclesDto, Pageable pageable) {
		Query query = new Query();
		
		if (searchVehiclesDto.getBrands() != null && !searchVehiclesDto.getBrands().isEmpty()) {
			query.addCriteria(Criteria.where(BRAND).in(searchVehiclesDto.getBrands()));
		}
		
		if (searchVehiclesDto.getMinPrice() != null && searchVehiclesDto.getMaxPrice() != null) {
			query.addCriteria(Criteria.where(PRICE)
					.gte(searchVehiclesDto.getMinPrice())
					.lte(searchVehiclesDto.getMaxPrice()));
		} else if (searchVehiclesDto.getMinPrice() != null) {
			query.addCriteria(Criteria.where(PRICE).gte(searchVehiclesDto.getMinPrice()));
		} else if (searchVehiclesDto.getMaxPrice() != null){
			query.addCriteria(Criteria.where(PRICE).lte(searchVehiclesDto.getMaxPrice()));
		}
		
		if (searchVehiclesDto.getCondition() != null && !searchVehiclesDto.getCondition().isEmpty()) {
			if (searchVehiclesDto.getCondition().contains("New") && !searchVehiclesDto.getCondition().contains("Used")) {
				query.addCriteria(Criteria.where(NEW_VEHICLE).is(true));
			} else if (!searchVehiclesDto.getCondition().contains("New") && searchVehiclesDto.getCondition().contains("Used")) {
				query.addCriteria(Criteria.where(NEW_VEHICLE).is(false));
			}
		}
		
		if (searchVehiclesDto.getLocationDetails() != null) {
			Criteria locationCriteria = getLocationSearchCriteria(searchVehiclesDto.getLocationDetails());
			
			query.addCriteria(locationCriteria);
		}
		
//		query.with(pageable);
		
//		query.with(new Sort(new Order(Direction.ASC, "location")));

		
		List<Vehicle> vehicles = mongoTemplate.find(query,Vehicle.class);

		return PageableExecutionUtils.getPage(
				vehicles, 
				pageable,
				() -> mongoTemplate.count(query, Vehicle.class));

	}
	
	private Criteria getLocationSearchCriteria(LocationDetails locationDetails) {
		Point point = new Point(locationDetails.getLocation()[0], locationDetails.getLocation()[1]);
		Distance radius = new Distance(locationDetails.getDistance(), Metrics.KILOMETERS);
		Circle circle = new Circle(point, radius);
		
		return Criteria.where("location").withinSphere(circle);
	}

}
