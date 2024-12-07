package com.securepro.secure.service.interfaces;

import com.securepro.secure.model.dto.request.CategoryRequestDTO;
import com.securepro.secure.model.dto.response.CategoryResponseDTO;
import com.securepro.secure.model.dto.response.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CategoryService {

    Page<CategoryResponseDTO> getCategories(PageRequest pageRequest);

    Page<CategoryResponseDTO> searchCategoriesByName(String name, PageRequest pageRequest);

    Page<ProductResponseDTO> getProductsByCategory(Long categoryId, PageRequest pageRequest);

    CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);

    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO);

    void deleteCategory(Long id);
}