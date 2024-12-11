package com.securepro.secure.repository;

import com.securepro.secure.model.entity.Category;
import com.securepro.secure.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findByNameContainingIgnoreCase(String name, PageRequest pageRequest);

    Optional<Category> findById(Long id);

    Page<Product> findProductsByCategory(Category category, PageRequest pageRequest);
}