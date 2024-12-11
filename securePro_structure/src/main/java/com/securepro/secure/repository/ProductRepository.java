package com.securepro.secure.repository;

import com.securepro.secure.model.entity.Category;
import com.securepro.secure.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findProductsByCategory(Category category, PageRequest pageRequest);
}
