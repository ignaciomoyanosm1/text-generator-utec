package com.iugo.demo.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    @NotBlank(message = "El nombre no debe estar vacío")
    private String name;

    @NotBlank(message = "El cargo no debe estar vacío")
    private String position;
}
