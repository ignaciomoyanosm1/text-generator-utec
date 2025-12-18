package com.iugo.demo.dto.request;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContextDTO {

    @NotNull
    private LocalDate date;

    @NotBlank
    private String procedureNumber;

    @NotBlank
    private String procedureType;

    @NotBlank
    private String purchaseItem;

    @NotBlank
    private String requestingUnit;

    @NotNull
    @Valid
    private PersonDTO requester;

    @NotNull
    @Valid
    private PersonDTO supervisor;

    @NotBlank
    private String legalBasis;

    @NotNull
    private Boolean appliesArticle72;

    @Size(max = 2000)
    private String additionalComments;

}
