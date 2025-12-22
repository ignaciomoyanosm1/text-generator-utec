package com.iugo.demo.dto.request.email;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalSpecificationDTO {


    @NotBlank(message = "La descripción de la especificación técnica no debe estar vacía")
    private String description;

    @NotBlank(message = "El puntaje de la especificación técnica no debe estar vacío")
    private String rating;
}
