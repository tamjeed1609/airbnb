package com.ums.controller;

import com.ums.entity.AppUser;
import com.ums.entity.Booking;
import com.ums.entity.Property;
import com.ums.repository.BookingRepository;
import com.ums.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private PropertyRepository prep;

    @Autowired
    private BookingRepository brep;
    @PostMapping("/createBooking")
  public ResponseEntity<Booking> createBooking(@AuthenticationPrincipal AppUser user, @RequestParam long id, @RequestBody Booking booking){
        Property property = prep.findById(id).get();
        booking.setTotalPrice(property.getNightlyPrice()* booking.getTotalNights());
booking.setProperty(property);
booking.setAppUser(user);
        Booking save = brep.save(booking);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
  }
}
