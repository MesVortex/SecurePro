package com.securepro.secure.service;

import com.securepro.secure.model.dto.request.CategoryRequestDTO;
import com.securepro.secure.model.dto.response.CategoryResponseDTO;
import com.securepro.secure.model.dto.response.ProductResponseDTO;
import com.securepro.secure.model.entity.Category;
import com.securepro.secure.model.entity.Product;
import com.securepro.secure.model.mapper.CategoryMapper;
import com.securepro.secure.repository.CategoryRepository;
import com.securepro.secure.repository.ProductRepository;
import com.securepro.secure.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;

    // Get all categories with pagination and sorting
    @Override
    public Page<CategoryResponseDTO> getCategories(PageRequest pageRequest) {
        Page<Category> categories = categoryRepository.findAll(pageRequest);
        return categories.map(category -> categoryMapper.map(category, CategoryResponseDTO.class));
    }

    // Search categories by name with pagination and sorting
    @Override
    public Page<CategoryResponseDTO> searchCategoriesByName(String name, PageRequest pageRequest) {
        Page<Category> categories = categoryRepository.findByNameContaining(name, pageRequest);
        return categories.map(category -> categoryMapper.map(category, CategoryResponseDTO.class));
    }

    // Get products by category with pagination and sorting
    @Override
    public Page<ProductResponseDTO> getProductsByCategory(Long categoryId, PageRequest pageRequest) {
        Page<Product> products = productRepository.findByCategoryId(categoryId, pageRequest);
        return products.map(product -> categoryMapper.map(product, ProductResponseDTO.class));
    }

    // Create a new category (Admin only)
    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryMapper.map(categoryRequestDTO, Category.class);
        category = categoryRepository.save(category);
        return categoryMapper.map(category, CategoryResponseDTO.class);
    }

    // Update an existing category (Admin only)
    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        categoryMapper.map(categoryRequestDTO, category); // Update category details
        category = categoryRepository.save(category);
        return categoryMapper.map(category, CategoryResponseDTO.class);
    }

    // Delete a category (Admin only)
    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);
    }
}