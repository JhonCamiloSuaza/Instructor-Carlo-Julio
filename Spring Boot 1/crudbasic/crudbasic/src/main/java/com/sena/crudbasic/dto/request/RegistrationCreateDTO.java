package com.sena.crudbasic.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationCreateDTO {

    @PastOrPresent(message = "La fecha de registro no puede ser futura")
    private LocalDate registeredOn;

    @NotNull(message = "El ID del aprendiz es obligatorio")
    private Integer learnerId;

    @NotNull(message = "El ID del módulo de formación es obligatorio")
    private Integer trainingModuleId;
}
