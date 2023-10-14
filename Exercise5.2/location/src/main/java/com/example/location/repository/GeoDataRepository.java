package com.example.location.repository;

import com.example.location.model.GeoData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeoDataRepository extends CrudRepository<GeoData, Integer> {
    Optional<GeoData> findByName(String name);
}
