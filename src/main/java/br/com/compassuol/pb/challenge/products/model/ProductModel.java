package br.com.compassuol.pb.challenge.products.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.Check;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.uuid.Generators;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "tb_product")
@Getter
@Setter
public class ProductModel {
    
    @Id
    @Column(name = "id")
    private String uuid;

    private LocalDate date;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, unique = true)
    @Check(constraints = "LENGTH(name)>=3")
    private String name;

    private String imgUrl;

    @Positive(message = "O valor deve ser maior que zero")
    private Double price;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "tb_product_category",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CategoryModel> categories = new ArrayList<>();;

    public ProductModel() {
        this.uuid = Generators.randomBasedGenerator().generate().toString();
    }
}
