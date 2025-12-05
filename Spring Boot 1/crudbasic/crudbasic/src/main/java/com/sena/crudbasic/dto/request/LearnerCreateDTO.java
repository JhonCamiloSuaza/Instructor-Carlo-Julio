package com.sena.crudbasic.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LearnerCreateDTO {

    @NotBlank(message = "El nombre completo es obligatorio")
    private String fullName;

    @NotNull(message = "El ID del usuario del sistema es obligatorio")
    @Positive(message = "El ID del usuario debe ser un número positivo")
    private Integer systemUserId;
}
