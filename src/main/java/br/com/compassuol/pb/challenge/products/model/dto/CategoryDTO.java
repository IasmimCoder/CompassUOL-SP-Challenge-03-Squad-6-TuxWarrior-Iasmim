package br.com.compassuol.pb.challenge.products.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    
    private String id;

    @NotEmpty(message = "Name is required")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

}
