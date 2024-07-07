package com.ums.repository;

import com.ums.entity.AppUser;
import com.ums.entity.Favourite;
import com.ums.entity.Property;
import com.ums.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    @Query("select f from Favourite f where f.appUser=:user and f.property=:property")
    Favourite findFavourite(@Param("user") AppUser user, @Param("property") Property property);

    @Query("select f from Favourite f where f.appUser=:user")
    List<Favourite> listFavOfUser(@Param("user") AppUser user);


}