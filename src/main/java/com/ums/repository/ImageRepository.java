package com.ums.repository;

import com.ums.entity.Image;
import com.ums.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("select i from Image i where i.property=:property")
    List<Image> getByProperty(@Param("property")Property property);
}