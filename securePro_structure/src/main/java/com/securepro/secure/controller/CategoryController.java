package com.securepro.secure.controller;

import com.securepro.secure.model.dto.request.CategoryRequestDTO;
import com.securepro.secure.model.dto.response.CategoryResponseDTO;
import com.securepro.secure.service.interfaces.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // List Categories with pagination and sorting (USER or ADMIN)
    @GetMapping("/user/categories")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<CategoryResponseDTO>> getCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String sort) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort.split(",")));
        return ResponseEntity.ok(categoryService.getCategories(pageRequest));
    }

    // Search Categories by name with pagination and sorting (USER or ADMIN)
    @GetMapping("/user/categories/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<CategoryResponseDTO>> searchCategoriesByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String sort) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort.split(",")));
        return ResponseEntity.ok(categoryService.searchCategoriesByName(name, pageRequest));
    }

    // List Products of a Category with pagination and sorting (USER or ADMIN)
    @GetMapping("/user/categories/{categoryId}/products")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<ProductResponseDTO>> getProductsByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String sort) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort.split(",")));
        return ResponseEntity.ok(categoryService.getProductsByCategory(categoryId, pageRequest));
    }

    // Add a new Category (ADMIN only)
    @PostMapping("/admin/categories")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return new ResponseEntity<>(categoryService.createCategory(categoryRequestDTO), HttpStatus.CREATED);
    }

    // Update an existing Category (ADMIN only)
    @PutMapping("/admin/categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryRequestDTO));
    }

    // Delete a Category (ADMIN only)
    @DeleteMapping("/admin/categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}