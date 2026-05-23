package com.tfi.Econexo.service.impl;

import com.tfi.Econexo.model.location.Neighborhood;
import com.tfi.Econexo.repository.location.NeighborhoodRepository;
import com.tfi.Econexo.service.NeighborhoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NeighborhoodServiceImpl implements NeighborhoodService {

    private final NeighborhoodRepository neighborhoodRepository;

    @Override
    public List<Neighborhood> findAll() {
        return neighborhoodRepository.findAll();
    }

}
