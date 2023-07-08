package br.com.compassuol.pb.challenge.products.mapper;

import org.springframework.stereotype.Component;

import br.com.compassuol.pb.challenge.products.model.CategoryModel;
import br.com.compassuol.pb.challenge.products.model.dto.CategoryDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryModel toEntity(CategoryDTO dto) {
        CategoryModel categoryModel = new CategoryModel();
        // categoryModel.setUuid(dto.getUuid());
        categoryModel.setName(dto.getName());
        return categoryModel;
    }

    public CategoryDTO toDto(CategoryModel categoryModel) {
        CategoryDTO dto = new CategoryDTO();
        dto.setUuid(categoryModel.getUuid());
        dto.setName(categoryModel.getName());
        return dto;
    }


    public List<CategoryDTO> toDto(List<CategoryModel> listCategoryModels) {
        return listCategoryModels.stream().map(this::toDto).collect(Collectors.toList());
    }
}