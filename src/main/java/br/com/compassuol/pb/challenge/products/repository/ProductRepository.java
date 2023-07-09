package br.com.compassuol.pb.challenge.products.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compassuol.pb.challenge.products.model.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, String> {

    Optional<ProductModel> findByName(String name);
    
}
