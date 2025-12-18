package com.iugo.demo.dto.request.email;

import com.iugo.demo.dto.request.ContextDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateEmailProviderRequestDTO {
    @NotNull
    @Valid
    private ContextDTO context;

    @NotNull
    @Valid
    private EmailProviderDTO emailProvider;
}
