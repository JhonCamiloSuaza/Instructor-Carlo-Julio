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
public class TeacherModuleUpdateDTO {

    @NotNull(message = "El ID del docente es obligatorio")
    private Integer teacherId;

    @NotNull(message = "El ID del módulo de formación es obligatorio")
    private Integer trainingModuleId;
}
