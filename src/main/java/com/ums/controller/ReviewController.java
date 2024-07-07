package com.ums.controller;

import com.ums.entity.AppUser;
import com.ums.entity.Property;
import com.ums.entity.Review;
import com.ums.repository.PropertyRepository;
import com.ums.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    @Autowired
    private PropertyRepository prep;
    @Autowired
    private ReviewRepository rrep;

    @PostMapping("/{id}")
    public ResponseEntity<String> addReview(@AuthenticationPrincipal AppUser user, @RequestBody Review review, @PathVariable long id){
        Property property = prep.findById(id).get();
        Review review1 = rrep.findReview(user, property);
        if(review1==null){
            review.setProperty(property);
            review.setAppUser(user);
            rrep.save(review);
        return new ResponseEntity<>("Review saved successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Review Already exists",HttpStatus.BAD_REQUEST);

    }
}
