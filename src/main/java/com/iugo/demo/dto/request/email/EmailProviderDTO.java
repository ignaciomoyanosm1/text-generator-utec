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

    @NotBlank(message = "El nombre del destinatario no debe estar vacío")
    private String recipientName;

    @NotNull(message = "La fecha límite de cotización es obligatoria")
    private LocalDate quotationDeadline;

    @NotBlank(message = "El lugar de entrega no debe estar vacío")
    private String deliveryLocation;

    @NotNull(message = "El plazo de pago es obligatorio")
    @Min(value = 1, message = "El plazo de pago debe ser mayor o igual a 1 día")
    private Integer paymentDueDays;

    @NotEmpty(message = "Debe incluir al menos una especificación técnica")
    @Valid
    private List<TechnicalSpecificationDTO> technicalSpecifications;

    @Size(max = 2000, message = "Las notas no pueden superar los 2000 caracteres")
    private String notes;
}
