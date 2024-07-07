package com.ums.controller;

import com.ums.entity.AppUser;
import com.ums.entity.Favourite;
import com.ums.entity.Property;
import com.ums.repository.FavouriteRepository;
import com.ums.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fav")
public class FavouriteController {

    @Autowired
    private PropertyRepository prep;

    @Autowired
    private FavouriteRepository frep;

    @PostMapping("/{id}")
    public ResponseEntity<String> addFavourite(@AuthenticationPrincipal AppUser user, @PathVariable long id){
        Property property = prep.findById(id).get();
        Favourite favourite = frep.findFavourite(user, property);
        if(favourite==null){
            Favourite f=new Favourite();
            f.setIsFavourite(true);
            f.setProperty(property);
            f.setAppUser(user);
            frep.save(f);
            return new ResponseEntity<>("Favourite saved successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Favourite already exists",HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public List<Favourite> getAllFavourites(@AuthenticationPrincipal AppUser user){
        List<Favourite> favourites = frep.listFavOfUser(user);
        return favourites;

    }

}
