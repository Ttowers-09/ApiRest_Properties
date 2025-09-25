package com.properties.manage_properties.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.properties.manage_properties.repository.*;
import com.properties.manage_properties.model.*;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }
    
    public Property createProperty (Property property) {
        return propertyRepository.save(property);
    }

    public List <Property> findByLocation (String location) {
        return propertyRepository.findByLocation(location);
    }

    public List <Property> findAll () {
        return propertyRepository.findAll();
    }

    public Property findById (Long id) {
        return propertyRepository.findById(id).orElse(null);
    }

    public void deleteById (Long id) {
        if (propertyRepository.findById(id).isEmpty()) {
            throw new IllegalStateException("La propiedad con id " + id + " no existe");
        }else {
            propertyRepository.deleteById(id);
        }
    }

    public List <Property> findPropertyBetweenPrice (int minPrice, int maxPrice) {
        return propertyRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public Property updateProperty (Long id, Property property) {
        Property propertyUpdate = propertyRepository.findById(id).orElse(null);
         if (propertyUpdate == null) {
           return null;
        }else {
            propertyUpdate.setAddress(property.getAddress());
            propertyUpdate.setPrice(property.getPrice());
            propertyUpdate.setSize(property.getSize());
            propertyUpdate.setDescription(property.getDescription());
            propertyUpdate.setActualOwner(property.getActualOwner());
            propertyUpdate.setLocation(property.getLocation());
            return propertyRepository.save(propertyUpdate);
        }

    }

    public List <Property> findPropertyBetweenSize (Integer minSize, Integer maxSize) {
        return propertyRepository.findBySizeBetween(minSize, maxSize);
    }
}
