package com.iugo.demo.dto.request.email;

import com.iugo.demo.dto.request.ContextDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateEmailProviderRequestDTO {
    @NotNull(message = "El contexto es obligatorio")
    @Valid
    private ContextDTO context;

    @NotNull(message = "Los datos del proveedor son obligatorios")
    @Valid
    private EmailProviderDTO emailProvider;
}
