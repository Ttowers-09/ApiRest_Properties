package com.properties.manage_properties.controller;


import com.properties.manage_properties.model.Property;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.properties.manage_properties.service.PropertyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/properties")
@CrossOrigin(origins = "*")

public class PropertyController {
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Property request) {
        Property property = propertyService.createProperty(request);
        return new ResponseEntity<>(property, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Property property = propertyService.findById(id);
        if (property == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Property id " + id + " not found");
        }
        return ResponseEntity.ok(property);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(propertyService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Property request) {
        Property property = propertyService.updateProperty(id, request);
        if (property == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Property id " + id + " not found");
        }
        return ResponseEntity.ok(property);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            propertyService.deleteById(id);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Property id " + id + " not found");
        }
        return null;
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<?> getByLocation(@PathVariable String location) {
        if (location == null || location.isEmpty() || propertyService.findByLocation(location).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No properties found in location: " + location);
        }
        return ResponseEntity.ok(propertyService.findByLocation(location));
    }

    @GetMapping("/price-range/{minPrice}/{maxPrice}")
    public ResponseEntity<?> getByPriceRange(@PathVariable int minPrice, @PathVariable int maxPrice) {
        if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice || propertyService.findPropertyBetweenPrice(minPrice, maxPrice).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No properties found in the price range: " + minPrice + " - " + maxPrice);
        }
        return ResponseEntity.ok(propertyService.findPropertyBetweenPrice(minPrice, maxPrice));
    }

    @GetMapping("/size-range/{minSize}/{maxSize}")
    public ResponseEntity<?> getBySizeRange(@PathVariable Integer minSize, @PathVariable Integer maxSize) {
        if (minSize < 0 || maxSize < 0 || minSize > maxSize || propertyService.findPropertyBetweenSize(minSize, maxSize).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No properties found in the size range: " + minSize + " - " + maxSize);
        }
        return ResponseEntity.ok(propertyService.findPropertyBetweenSize(minSize, maxSize));
    }

}
