package com.ums.repository;

import com.ums.entity.AppUser;
import com.ums.entity.Property;
import com.ums.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.appUser=:user and r.property=:property")
    Review findReview(@Param("user") AppUser user, @Param("property") Property property);
}