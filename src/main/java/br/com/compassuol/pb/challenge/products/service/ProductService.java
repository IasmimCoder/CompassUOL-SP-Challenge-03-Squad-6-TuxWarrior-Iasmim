package br.com.compassuol.pb.challenge.products.service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compassuol.pb.challenge.products.exceptions.EntityAlreadyExistsException;
import br.com.compassuol.pb.challenge.products.exceptions.EntityNotFoundException;
import br.com.compassuol.pb.challenge.products.mapper.ProductMapper;
import br.com.compassuol.pb.challenge.products.model.CategoryModel;
import br.com.compassuol.pb.challenge.products.model.ProductModel;
import br.com.compassuol.pb.challenge.products.model.dto.CreateProductDTO;
import br.com.compassuol.pb.challenge.products.model.dto.ProductDTO;
import br.com.compassuol.pb.challenge.products.model.dto.UpdateProductDTO;
import br.com.compassuol.pb.challenge.products.model.enums.DirectionSort;
import br.com.compassuol.pb.challenge.products.repository.CategoryRepository;
import br.com.compassuol.pb.challenge.products.repository.ProductRepository;
import br.com.compassuol.pb.challenge.products.utils.MessageUtils;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper mapper;

    @Transactional
    public ProductDTO save(CreateProductDTO dto) {
        Optional<ProductModel> optional = repository.findByName(dto.getName());

        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("Product" + MessageUtils.ALREADY_EXIST);
        }

        List<CategoryModel> list = new ArrayList<>();
        if (dto.getCategories() != null) {
            for (String id : dto.getCategories()) {
                Optional<CategoryModel> optionalModel = categoryRepository.findById(id);
                if (!optionalModel.isPresent()) {
                    throw new EntityAlreadyExistsException("Category" + MessageUtils.NO_RECORDS_FOUND);
                }
                list.add(optionalModel.get());
            }
        }

        ProductModel productModel = mapper.toEntity(dto, list);
        repository.save(productModel);
        return mapper.toDto(productModel);
    }

    @Transactional
    public ProductDTO update(String id, UpdateProductDTO dto) {
        // Este put não adicionará e nem editará as categorias do produto

        ProductDTO foundDto = this.findById(id);
        ProductModel model = mapper.toEntity(dto, foundDto);
        repository.save(model);
        return mapper.toDto(model);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(
            Integer page,
            Integer linesPerPage,
            String orderBy,
            String direction
            ) {
        PageRequest pageRequest = PageRequest.of(
            page,
            linesPerPage,
            Direction.valueOf(DirectionSort.fromString(direction).getDescription()),
            orderBy
        );

        return mapper.toDto(repository.findAll(pageRequest));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(String id) {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Product" + MessageUtils.NO_RECORDS_FOUND));
    }

    @Transactional
    public String delete(String id) {
        this.findById(id);
        repository.deleteById(id);
        return "Product" + MessageUtils.DELETED_WITH_SUCESS;
    }
}
