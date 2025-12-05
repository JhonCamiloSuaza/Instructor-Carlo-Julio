package com.sena.crudbasic.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class ProgramTypeUpdateDTO {

    @NotBlank(message = "El nombre del tipo de programa es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre del tipo de programa debe tener entre 2 y 100 caracteres")
    private String typeName;
}
