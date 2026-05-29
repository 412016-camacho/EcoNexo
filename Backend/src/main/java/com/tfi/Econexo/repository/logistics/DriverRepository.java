package com.tfi.Econexo.repository.logistics;

import com.tfi.Econexo.model.logistics.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
