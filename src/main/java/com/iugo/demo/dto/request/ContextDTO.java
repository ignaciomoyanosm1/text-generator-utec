package com.iugo.demo.dto.request;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContextDTO {

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate date;

    @NotBlank(message = "El número de procedimiento no debe estar vacío")
    private String procedureNumber;

    @NotBlank(message = "El tipo de procedimiento no debe estar vacío")
    private String procedureType;

    @NotBlank(message = "El objeto de compra no debe estar vacío")
    private String purchaseItem;

    @NotBlank(message = "La unidad solicitante no debe estar vacía")
    private String requestingUnit;

    @NotNull(message = "Los datos del solicitante son obligatorios")
    @Valid
    private PersonDTO requester;

    @NotNull(message = "Los datos del supervisor son obligatorios")
    @Valid
    private PersonDTO supervisor;

    @NotBlank(message = "El fundamento legal no debe estar vacío")
    private String legalBasis;

    @NotNull(message = "Debe indicar si aplica el Artículo 72 del TOCAF")
    private Boolean appliesArticle72;

    @Size(max = 2000, message = "Los comentarios adicionales no pueden superar los 2000 caracteres")
    private String additionalComments;

}
