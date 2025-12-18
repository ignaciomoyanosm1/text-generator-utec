package com.iugo.demo.dto.request.email;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalSpecificationDTO {

    @NotBlank
    private String description;

    @NotBlank
    private String rating;
}
