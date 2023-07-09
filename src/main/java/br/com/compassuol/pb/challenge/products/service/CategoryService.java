package br.com.compassuol.pb.challenge.products.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compassuol.pb.challenge.products.exceptions.EntityAlreadyExistsException;
import br.com.compassuol.pb.challenge.products.mapper.CategoryMapper;
import br.com.compassuol.pb.challenge.products.model.CategoryModel;
import br.com.compassuol.pb.challenge.products.model.dto.CategoryDTO;
import br.com.compassuol.pb.challenge.products.repository.CategoryRepository;
import br.com.compassuol.pb.challenge.products.utils.MessageUtils;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
    
    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryMapper mapper;

    @Transactional
    public CategoryDTO save(CategoryDTO dto) {
        Optional<CategoryModel> optional = repository.findByName(dto.getName());
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("Category" + MessageUtils.ALREADY_EXIST);
        }
        CategoryModel model = mapper.toEntity(dto);
        repository.save(model);
        return mapper.toDto(model);
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }
}
