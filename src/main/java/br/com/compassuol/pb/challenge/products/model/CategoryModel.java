package br.com.compassuol.pb.challenge.products.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Check;

import com.fasterxml.uuid.Generators;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "tb_category")
@Getter
@Setter
public class CategoryModel {
    
    @Id
    @Column(name = "id")
    private String uuid;

    @Column(nullable = false, unique = true)
    @Check(constraints = "LENGTH(name)>=3")
    private String name;

    public CategoryModel() {
        this.uuid = Generators.randomBasedGenerator().generate().toString();
    }
}
