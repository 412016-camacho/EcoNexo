package com.tfi.Econexo.repository.donation.catalog;

import com.tfi.Econexo.model.donation.catalog.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
