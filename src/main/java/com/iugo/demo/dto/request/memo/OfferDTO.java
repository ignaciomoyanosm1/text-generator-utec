package com.iugo.demo.dto.request.memo;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferDTO {

    @NotBlank(message = "El proveedor no debe estar vacío")
    private String supplier;

    @NotBlank(message = "La modalidad no debe estar vacía")
    private String modality;

    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio unitario debe ser mayor a 0")
    private BigDecimal unitPrice;

    @NotBlank(message = "La moneda no debe estar vacía")
    private String currency;
}
