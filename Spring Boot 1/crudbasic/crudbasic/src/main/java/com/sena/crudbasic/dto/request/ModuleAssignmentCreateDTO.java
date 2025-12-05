package com.sena.crudbasic.dto.request;

import jakarta.validation.constraints.NotNull;
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
public class ModuleAssignmentCreateDTO {

    @NotNull(message = "El ID del tipo de programa es obligatorio")
    private Integer programTypeId;

    @NotNull(message = "El ID del módulo de entrenamiento es obligatorio")
    private Integer trainingModuleId;
}
