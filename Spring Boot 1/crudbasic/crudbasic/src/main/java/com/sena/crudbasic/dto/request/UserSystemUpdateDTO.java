package com.sena.crudbasic.dto.request;

import jakarta.validation.constraints.*;
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
public class UserSystemUpdateDTO {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre de usuario debe tener entre 2 y 50 caracteres")
    private String loginName;
}
