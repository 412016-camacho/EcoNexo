package com.tfi.Econexo.service;

import com.tfi.Econexo.model.location.Neighborhood;

import java.util.List;
import java.util.Optional;

public interface NeighborhoodService {

    List<Neighborhood> findAll();
    Optional<Neighborhood> findById(Long id);
}
