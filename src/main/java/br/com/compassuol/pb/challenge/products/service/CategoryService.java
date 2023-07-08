package br.com.compassuol.pb.challenge.products.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compassuol.pb.challenge.products.exceptions.BusinessExceptions;
import br.com.compassuol.pb.challenge.products.mapper.CategoryMapper;
import br.com.compassuol.pb.challenge.products.model.CategoryModel;
import br.com.compassuol.pb.challenge.products.model.dto.CategoryDTO;
import br.com.compassuol.pb.challenge.products.repository.CategoryRepository;
import br.com.compassuol.pb.challenge.products.utils.MessageUtils;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryMapper mapper;

    @Transactional
    public CategoryDTO save(CategoryDTO dto) {
        Optional<CategoryModel> optionalCategoryOptional = repository.findByName(dto.getName());
        if (optionalCategoryOptional.isPresent()) {
            throw new BusinessExceptions(MessageUtils.PRODUCT_ALREADY_EXIST);
        }
        CategoryModel categoryModel = mapper.toEntity(dto);
        repository.save(categoryModel);
        return mapper.toDto(categoryModel);
    }
}
