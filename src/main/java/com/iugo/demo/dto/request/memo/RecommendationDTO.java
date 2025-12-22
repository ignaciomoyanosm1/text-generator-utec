package com.iugo.demo.dto.request.memo;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationDTO {
    @NotBlank(message = "El proveedor recomendado no debe estar vacío")
    private String supplier;

    @NotBlank(message = "El equipo recomendado no debe estar vacío")
    private String equipment;

    @NotNull(message = "El precio recomendado es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio recomendado debe ser mayor a 0")
    private BigDecimal price;

    @NotBlank(message = "La moneda de la recomendación no debe estar vacía")
    private String currency;
}
