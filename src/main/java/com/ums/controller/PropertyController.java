package com.ums.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.ums.entity.Property;
import com.ums.exception.ResourceNotFound;
import com.ums.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    @Autowired
    private PropertyRepository prep;

    @GetMapping
    public ResponseEntity<List<Property>> listPropertyByLocationNameOrCountryName(@RequestParam String name) {
        List<Property> properties = prep.getPropertyByLocationNameOrCountryName(name);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> findPropertyById(@PathVariable long id) {
        Property property = prep.findById(id).orElseThrow(() -> new ResourceNotFound("Property not found with Id " + id));
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Property>> findAllProperties(
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(name = "pageNo", defaultValue = "5", required = false) int pageNo,
            @RequestParam(name = "sortBy", defaultValue = "5", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        Pageable pageable = null;
        if (sortDir.equalsIgnoreCase("asc")) {
            pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());

        }
        else if (sortDir.equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        }
        Page<Property> all1 = prep.findAll(pageable);
        List<Property> all = all1.getContent();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}