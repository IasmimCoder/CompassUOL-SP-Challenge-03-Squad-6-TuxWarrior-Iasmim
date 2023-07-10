package br.com.compassuol.pb.challenge.users.model.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserDTO {
    
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

    private List<String> categories;

}