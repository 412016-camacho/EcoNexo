package com.tfi.Econexo.service.impl.donation;

import com.tfi.Econexo.dto.donation.catalog.CategoryDTO;
import com.tfi.Econexo.dto.donation.catalog.ProductDTO;
import com.tfi.Econexo.dto.donation.catalog.UnitOfMeasureDTO;
import com.tfi.Econexo.model.donation.catalog.Category;
import com.tfi.Econexo.model.donation.catalog.Product;
import com.tfi.Econexo.model.donation.catalog.UnitOfMeasure;
import com.tfi.Econexo.repository.donation.catalog.CategoryRepository;
import com.tfi.Econexo.repository.donation.catalog.ProductRepository;
import com.tfi.Econexo.repository.donation.catalog.UnitOfMeasureRepository;
import com.tfi.Econexo.service.donation.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(c -> new CategoryDTO(c.getId(), c.getDescription()))
                .toList();
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(p -> new ProductDTO(p.getId(),
                        p.getName(),
                        p.getCategory().getId(),
                        p.isRequiresRefrigeration(),
                        p.isOriginalPackaging()))
                .toList();
    }

    @Override
    public List<UnitOfMeasureDTO> getAllUnits() {
        return unitOfMeasureRepository.findAll().stream()
                .map(u -> new UnitOfMeasureDTO(u.getId(), u.getDescription()))
                .toList();
    }
}
