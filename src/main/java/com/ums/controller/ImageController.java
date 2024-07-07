package com.ums.controller;

import com.ums.entity.Image;
import com.ums.entity.Property;
import com.ums.repository.ImageRepository;
import com.ums.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    @Autowired
    private PropertyRepository prep;
    @Autowired
    private ImageRepository irep;

    @PostMapping("/{id}")
   public ResponseEntity<Image> addImage(@PathVariable long id){
        Property property = prep.findById(id).get();
        Image image=new Image();
        image.setImageUrl("https://unsplash.com/photos/a-bunch-of-balloons-that-are-shaped-like-email-7NT4EDSI5Ok");
        image.setProperty(property);
irep.save(image);
return new ResponseEntity<>(image, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Image>> getImagesByProperty(@RequestParam long id){
        Property property = prep.findById(id).get();
        List<Image> images = irep.getByProperty(property);
return new ResponseEntity<>(images,HttpStatus.OK);
    }

}
