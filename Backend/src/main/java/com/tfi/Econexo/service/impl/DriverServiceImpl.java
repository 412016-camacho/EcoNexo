package com.tfi.Econexo.service.impl;

import com.tfi.Econexo.repository.logistics.DriverRepository;
import com.tfi.Econexo.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
}
