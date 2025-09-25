package com.properties.manage_properties.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.properties.manage_properties.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    // Vamos acá añadir "funcionalidades" extras

    List <Property> findByLocation (String location);
    List <Property> findByPriceBetween (int minPrice, int maxPrice);
    List <Property> findBySizeBetween (Integer minSize, Integer maxSize);
}
