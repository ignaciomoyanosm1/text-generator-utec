package com.iugo.demo.dto.request.memo;

import com.iugo.demo.dto.request.ContextDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateMemoRequestDTO {

    @NotNull(message = "El contexto es obligatorio")
    @Valid
    private ContextDTO context;

    @NotEmpty(message = "Debe incluir al menos una oferta")
    @Valid
    private List<OfferDTO> offers;

    @NotNull(message = "La recomendación es obligatoria")
    @Valid
    private RecommendationDTO recommendation;
}
