package br.com.compassuol.pb.challenge.products.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compassuol.pb.challenge.products.exceptions.EntityAlreadyExistsException;
import br.com.compassuol.pb.challenge.products.mapper.ProductMapper;
import br.com.compassuol.pb.challenge.products.model.ProductModel;
import br.com.compassuol.pb.challenge.products.model.dto.CreateProductDTO;
import br.com.compassuol.pb.challenge.products.model.dto.ProductDTO;
import br.com.compassuol.pb.challenge.products.repository.ProductRepository;
import br.com.compassuol.pb.challenge.products.utils.MessageUtils;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
    
    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductMapper mapper;

    @Transactional
    public ProductDTO save(CreateProductDTO dto) {
        Optional<ProductModel> optional = repository.findByName(dto.getName());
        
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("Product" + MessageUtils.ALREADY_EXIST);
        }
        ProductModel productModel = mapper.toEntity(dto);
        repository.save(productModel);
        return mapper.toDto(productModel);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }
}
