package br.com.compassuol.pb.challenge.products.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compassuol.pb.challenge.products.model.CategoryModel;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, String> {

    Optional<CategoryModel> findByName(String name);
    
}
