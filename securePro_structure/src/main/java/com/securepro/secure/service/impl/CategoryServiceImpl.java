package com.securepro.secure.service.impl;

import com.securepro.secure.model.dto.request.CategoryRequestDTO;
import com.securepro.secure.model.dto.response.CategoryResponseDTO;
import com.securepro.secure.model.dto.response.ProductResponseDTO;
import com.securepro.secure.model.entity.Category;
import com.securepro.secure.model.mapper.CategoryMapper;
import com.securepro.secure.model.mapper.ProductMapper;
import com.securepro.secure.repository.CategoryRepository;
import com.securepro.secure.service.interfaces.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    @Override
    public Page<CategoryResponseDTO> getCategories(PageRequest pageRequest) {
        return categoryRepository.findAll(pageRequest)
                .map(categoryMapper::toResponse);
    }

    @Override
    public Page<CategoryResponseDTO> searchCategoriesByName(String name, PageRequest pageRequest) {
        return categoryRepository.findByNameContainingIgnoreCase(name, pageRequest)
                .map(categoryMapper::toResponse);
    }

    @Override
    public Page<ProductResponseDTO> getProductsByCategory(Long categoryId, PageRequest pageRequest) {
        return categoryRepository.findById(categoryId)
                .map(category -> categoryRepository.findProductsByCategory(category, pageRequest))
                .orElseThrow(() -> new EntityNotFoundException("Category not found"))
                .map(productMapper::toResponse);
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryMapper.toEntity(categoryRequestDTO);
        category = categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        category.setName(categoryRequestDTO.name());
        category = categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        categoryRepository.delete(category);
    }
}