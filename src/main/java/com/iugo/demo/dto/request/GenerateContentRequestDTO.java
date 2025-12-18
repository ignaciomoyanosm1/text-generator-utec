package com.iugo.demo.dto.request;
import com.iugo.demo.dto.request.email.EmailProviderDTO;
import com.iugo.demo.dto.request.memo.OfferDTO;
import com.iugo.demo.dto.request.memo.RecommendationDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateContentRequestDTO {

    @NotNull @Valid
    private ContextDTO context;

    @NotEmpty @Valid
    private List<OfferDTO> offers;

    @NotNull @Valid
    private RecommendationDTO recommendation;

    @NotNull @Valid
    private EmailProviderDTO emailProvider;
}
