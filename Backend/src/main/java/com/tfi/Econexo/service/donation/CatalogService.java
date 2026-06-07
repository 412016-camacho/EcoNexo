package com.tfi.Econexo.service.donation;

import com.tfi.Econexo.dto.donation.catalog.CategoryDTO;
import com.tfi.Econexo.dto.donation.catalog.ProductDTO;
import com.tfi.Econexo.dto.donation.catalog.UnitOfMeasureDTO;

import java.util.List;

public interface CatalogService {
    List<CategoryDTO> getAllCategories();
    List<ProductDTO> getAllProducts();
    List<UnitOfMeasureDTO> getAllUnits();
}
