package com.securepro.secure;

import com.securepro.secure.model.dto.request.CategoryRequestDTO;
import com.securepro.secure.model.dto.response.CategoryResponseDTO;
import com.securepro.secure.model.dto.response.ProductResponseDTO;
import com.securepro.secure.model.entity.Category;
import com.securepro.secure.model.entity.Product;
import com.securepro.secure.model.mapper.CategoryMapper;
import com.securepro.secure.model.mapper.ProductMapper;
import com.securepro.secure.repository.CategoryRepository;
import com.securepro.secure.service.impl.CategoryServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCategories_shouldReturnPageOfCategoryResponseDTO() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Category category = Category.builder().id(1L).name("Test Category").build();
        CategoryResponseDTO responseDTO = CategoryResponseDTO.builder().id(1L).name("Test Category").build();

        when(categoryRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(List.of(category)));
        when(categoryMapper.toResponse(category)).thenReturn(responseDTO);

        Page<CategoryResponseDTO> result = categoryService.getCategories(pageRequest);

        assertEquals(1, result.getContent().size());
        assertEquals(responseDTO, result.getContent().get(0));
        verify(categoryRepository).findAll(pageRequest);
        verify(categoryMapper).toResponse(category);
    }

    @Test
    void searchCategoriesByName_shouldReturnPageOfCategoryResponseDTO() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        String name = "Test";
        Category category = Category.builder().id(1L).name("Test Category").build();
        CategoryResponseDTO responseDTO = CategoryResponseDTO.builder().id(1L).name("Test Category").build();

        when(categoryRepository.findByNameContainingIgnoreCase(name, pageRequest))
                .thenReturn(new PageImpl<>(List.of(category)));
        when(categoryMapper.toResponse(category)).thenReturn(responseDTO);

        Page<CategoryResponseDTO> result = categoryService.searchCategoriesByName(name, pageRequest);

        assertEquals(1, result.getContent().size());
        assertEquals(responseDTO, result.getContent().get(0));
        verify(categoryRepository).findByNameContainingIgnoreCase(name, pageRequest);
        verify(categoryMapper).toResponse(category);
    }

//    @Test
//    void createCategory_shouldReturnCategoryResponseDTO() {
//        CategoryRequestDTO requestDTO = CategoryRequestDTO.builder().name("Test Category").build();
//        Category category = Category.builder().id(1L).name("Test Category").build();
//        CategoryResponseDTO responseDTO = CategoryResponseDTO.builder().id(1L).name("Test Category").build();
//
//        when(categoryMapper.toEntity(requestDTO)).thenReturn(category);
//        when(categoryRepository.save(category)).thenReturn(category);
//        when(categoryMapper.toResponse(category)).thenReturn(responseDTO);
//
//        CategoryResponseDTO result = categoryService.createCategory(requestDTO);
//
//        assertEquals(responseDTO, result);
//        verify(categoryMapper).toEntity(requestDTO);
//        verify(categoryRepository).save(category);
//        verify(categoryMapper).toResponse(category);
//    }

//    @Test
//    void updateCategory_shouldUpdateAndReturnCategoryResponseDTO() {
//        Long id = 1L;
//        CategoryRequestDTO requestDTO = CategoryRequestDTO.builder().name("Updated Name").build();
//        Category category = Category.builder().id(id).name("Old Name").build();
//        CategoryResponseDTO responseDTO = CategoryResponseDTO.builder().id(id).name("Updated Name").build();
//
//        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
//        when(categoryRepository.save(category)).thenReturn(category);
//        when(categoryMapper.toResponse(category)).thenReturn(responseDTO);
//
//        CategoryResponseDTO result = categoryService.updateCategory(id, requestDTO);
//
//        assertEquals(responseDTO, result);
//        assertEquals("Updated Name", category.getName());
//        verify(categoryRepository).findById(id);
//        verify(categoryRepository).save(category);
//        verify(categoryMapper).toResponse(category);
//    }

    @Test
    void deleteCategory_shouldDeleteCategory() {
        Long id = 1L;
        Category category = Category.builder().id(id).name("Test Category").build();

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));

        categoryService.deleteCategory(id);

        verify(categoryRepository).findById(id);
        verify(categoryRepository).delete(category);
    }

    @Test
    void deleteCategory_shouldThrowEntityNotFoundException() {
        Long id = 1L;

        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> categoryService.deleteCategory(id));

        verify(categoryRepository).findById(id);
        verify(categoryRepository, never()).delete(any());
    }
}