package com.iugo.demo.dto.request.email;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailProviderDTO {
    @NotBlank
    private String recipientName;

    @NotNull
    private LocalDate quotationDeadline;

    @NotBlank
    private String deliveryLocation;

    @NotNull
    @Min(1)
    private Integer paymentDueDays;

    @NotEmpty
    @Valid
    private List<TechnicalSpecificationDTO> technicalSpecifications;

    @Size(max = 2000)
    private String notes;
}
