package br.com.compassuol.pb.challenge.users.model.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Check;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.compassuol.pb.challenge.users.model.RoleModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    
    private String id;

    @NotEmpty(message = "First name is required")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String lastName;
    
	@NotEmpty(message = "Email is required")
    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    private List<RoleModel> roles;
}
