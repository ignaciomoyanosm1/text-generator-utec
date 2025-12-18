package com.iugo.demo.dto.request.memo;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationDTO {

    @NotBlank
    private String supplier;

    @NotBlank
    private String equipment;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotBlank
    private String currency;
}
