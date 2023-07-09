package br.com.compassuol.pb.challenge.products.mapper;

import org.springframework.stereotype.Component;

import br.com.compassuol.pb.challenge.products.model.CategoryModel;
import br.com.compassuol.pb.challenge.products.model.ProductModel;
import br.com.compassuol.pb.challenge.products.model.dto.ProductDTO;
import br.com.compassuol.pb.challenge.products.model.dto.UpdateProductDTO;
import br.com.compassuol.pb.challenge.products.model.dto.CreateProductDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductModel toEntity(CreateProductDTO dto, List<CategoryModel> list) {
        ProductModel model = new ProductModel();
        model.setName(dto.getName());
        model.setDate(dto.getDate());
        model.setDescription(dto.getDescription());
        model.setImgUrl(dto.getImgUrl());
        model.setPrice(dto.getPrice());

        model.setCategories(list);
        return model;
    }

    public ProductModel toEntity(UpdateProductDTO dto, ProductDTO founDto) {
        ProductModel model = new ProductModel();
        String name = dto.getName() == null ? founDto.getName() : dto.getName();
        LocalDate date = dto.getDate() == null ? founDto.getDate() : dto.getDate();
        String description = dto.getDescription() == null ? founDto.getDescription() : dto.getDescription();
        String imgUrl = dto.getImgUrl() == null ? founDto.getImgUrl() : dto.getImgUrl();
        Double price = dto.getPrice() == null ? founDto.getPrice() : dto.getPrice();

        model.setName(name);
        model.setDate(date);
        model.setDescription(description);
        model.setImgUrl(imgUrl);
        model.setPrice(price);
        model.setCategories(founDto.getCategories());

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
        dto.setCategories(model.getCategories()); // Observar que o getCategories está trazendo todos os produtos
                                                  // listados nas categories, embora não esteja passando no response por
                                                  // causa do @JsonIgnore
        return dto;
    }

    public List<ProductDTO> toDto(List<ProductModel> listModels) {
        return listModels.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}