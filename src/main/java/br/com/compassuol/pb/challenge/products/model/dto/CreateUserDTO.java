package br.com.compassuol.pb.challenge.users.model.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import br.com.compassuol.pb.challenge.users.anotations.AtLeastOneElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserDTO {
    
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

    // @AtLeastOneElement(message = "At least one roles is required")
    private List<String> roles;

}
