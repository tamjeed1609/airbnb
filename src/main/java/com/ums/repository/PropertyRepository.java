package com.ums.repository;

import com.ums.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

@Query("select p from Property p join Location l on p.location.id=l.id join Country c on p.country.id=c.id where l.locationName=:name or c.countryName=:name")
public List<Property> getPropertyByLocationNameOrCountryName(@Param("name") String name);
}