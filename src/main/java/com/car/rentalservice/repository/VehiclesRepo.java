package com.car.rentalservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.car.rentalservice.model.Vehicles;

public interface VehiclesRepo extends JpaRepository<Vehicles, Long>{

	List<Vehicles> findByVehicleType_VehicleTypeId(Long vehicleTypeId);

	Optional<Vehicles> findByVehicleIdentNumber(String vehicleIdentNumber);
	
	@Query(value ="SELECT *,ST_Distance(car_geom, ST_SetSRID(ST_MakePoint(:latitude, :longitude), 4326)) AS distance FROM vehicles where vehicle_status_code = 'AV'"
			+ " ORDER BY distance ASC OFFSET :pageOffset limit :pageSize ", nativeQuery = true)
	List<Object[]> findClosestVehicle(@Param("latitude") double latitude,@Param("longitude") double longitude,@Param("pageOffset") int pageOffset,@Param("pageSize") int pageSize);
	
}
