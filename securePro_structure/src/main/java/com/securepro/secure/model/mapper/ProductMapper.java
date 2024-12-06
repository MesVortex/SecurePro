package com.securepro.secure.model.mapper;

import com.securepro.secure.model.dto.request.ProductRequestDTO;
import com.securepro.secure.model.dto.response.ProductResponseDTO;
import com.securepro.secure.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class})
public interface ProductMapper {
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "categoryId", target = "category.id")
    Product toEntity(ProductRequestDTO dto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "category.id", target = "categoryId")
    ProductResponseDTO toResponse(Product entity);
}