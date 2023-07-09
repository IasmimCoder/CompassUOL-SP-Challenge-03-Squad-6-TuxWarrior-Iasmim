package br.com.compassuol.pb.challenge.products.model.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProductDTO {
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    
    private String description;

    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    private String imgUrl;

    @DecimalMin(value = "0.00")
    @Digits(integer = 6, fraction = 2)
    private Double price;

    private List<String> categories;

}
