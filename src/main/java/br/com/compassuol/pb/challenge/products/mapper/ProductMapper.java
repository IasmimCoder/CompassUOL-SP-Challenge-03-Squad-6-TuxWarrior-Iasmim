package br.com.compassuol.pb.challenge.products.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.compassuol.pb.challenge.products.exceptions.EntityAlreadyExistsException;
import br.com.compassuol.pb.challenge.products.model.CategoryModel;
import br.com.compassuol.pb.challenge.products.model.ProductModel;
import br.com.compassuol.pb.challenge.products.model.dto.ProductDTO;
import br.com.compassuol.pb.challenge.products.model.dto.CreateProductDTO;
import br.com.compassuol.pb.challenge.products.repository.CategoryRepository;
import br.com.compassuol.pb.challenge.products.utils.MessageUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    CategoryRepository repository;

    public ProductModel toEntity(CreateProductDTO dto) {
        ProductModel model = new ProductModel();
        model.setName(dto.getName());
        model.setDate(dto.getDate());
        model.setDescription(dto.getDescription());
        model.setImgUrl(dto.getImgUrl());
        model.setPrice(dto.getPrice());
        List<CategoryModel> list = new ArrayList<>();
        for (String id : dto.getCategories()) {
            Optional<CategoryModel> optional = repository.findById(id);
            if (!optional.isPresent()) {
                throw new EntityAlreadyExistsException("Category" + MessageUtils.NO_RECORDS_FOUND);
            }
            list.add(optional.get());
        }
        model.setCategories(list);
        return model;
    }

    public ProductDTO toDto(ProductModel model) {
        ProductDTO dto = new ProductDTO();
        dto.setId(model.getUuid());
        dto.setName(model.getName());
        dto.setDate(model.getDate());
        dto.setDescription(model.getDescription());
        dto.setImgUrl(model.getImgUrl());
        dto.setPrice(model.getPrice());
        dto.setCategories(model.getCategories()); // Observar que o getCategories está trazendo todos os produtos listados nas categories, embora não esteja passando no response por causa do @JsonIgnore
        return dto;
    }

    public List<ProductDTO> toDto(List<ProductModel> listModels) {
        return listModels.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }
}