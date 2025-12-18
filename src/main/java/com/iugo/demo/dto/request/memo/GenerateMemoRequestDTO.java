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
    @NotNull
    @Valid
    private ContextDTO context;

    @NotEmpty
    @Valid
    private List<OfferDTO> offers;

    @NotNull
    @Valid
    private RecommendationDTO recommendation;
}
