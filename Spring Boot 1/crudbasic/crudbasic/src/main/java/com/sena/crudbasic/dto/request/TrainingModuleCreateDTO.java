package com.sena.crudbasic.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingModuleCreateDTO {

    @NotBlank(message = "El título del módulo es obligatorio")
    @Size(min = 2, max = 150, message = "El título debe tener entre 2 y 150 caracteres")
    private String moduleTitle;

    @NotNull(message = "El costo del módulo es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El costo debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "Formato de costo inválido")
    private BigDecimal moduleCost;
}
