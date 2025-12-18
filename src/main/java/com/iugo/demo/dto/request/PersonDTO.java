package com.iugo.demo.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String position;
}
