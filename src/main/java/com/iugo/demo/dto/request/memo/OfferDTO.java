package com.iugo.demo.dto.request.memo;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferDTO {

    @NotBlank
    private String supplier;

    @NotBlank
    private String modality;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal unitPrice;

    @NotBlank
    private String currency;
}
