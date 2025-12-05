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
public class UserPermissionUpdateDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    @Positive(message = "El ID del usuario debe ser positivo")
    private Integer systemUserId;

    @NotNull(message = "El ID del permiso es obligatorio")
    @Positive(message = "El ID del permiso debe ser positivo")
    private Integer permissionId;
}