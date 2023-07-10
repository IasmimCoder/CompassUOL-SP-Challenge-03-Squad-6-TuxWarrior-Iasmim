package br.com.compassuol.pb.challenge.users.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Check;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.uuid.Generators;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "tb_role")
@Getter
@Setter
public class RoleModel {
    
    @Id
    @Column(name = "id")
    private String uuid;

    @Column(nullable = false, unique = true)
    @Check(constraints = "LENGTH(name)>=3")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<UserModel> users = new ArrayList<>();
   

    public RoleModel() {
        this.uuid = Generators.randomBasedGenerator().generate().toString();
    }
}
