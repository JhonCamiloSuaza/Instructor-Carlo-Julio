package com.sena.crudbasic.dto.request;

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
public class ModuleAssignmentUpdateDTO {

    @NotNull(message = "El ID del tipo de programa es obligatorio")
    @Positive(message = "El ID del tipo de programa debe ser positivo")
    private Integer programTypeId;

    @NotNull(message = "El ID del módulo de entrenamiento es obligatorio")
    @Positive(message = "El ID del módulo debe ser positivo")
    private Integer trainingModuleId;
}
