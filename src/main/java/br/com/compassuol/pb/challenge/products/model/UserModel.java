package br.com.compassuol.pb.challenge.products.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Check;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.uuid.Generators;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "tb_user")
@Getter
@Setter
public class UserModel {
    
    @Id
    @Column(name = "id")
    private String uuid;

    @Column(nullable = false, name = "first_name")
    @Check(constraints = "LENGTH(first_name)>3")
    private String firstName;

    
    @Column(nullable = false, name = "last_name")
    @Check(constraints = "LENGTH(last_name)>3")
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "tb_user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleModel> roles = new ArrayList<>();;

    public UserModel() {
        this.uuid = Generators.randomBasedGenerator().generate().toString();
    }
}
