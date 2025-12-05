package com.sena.crudbasic.dto.response;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonView;
import com.sena.crudbasic.dto.view.JsonViews;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPermissionResponseDTO {

    @JsonView(JsonViews.Resumen.class)
    private Integer id;

    @JsonView(JsonViews.Resumen.class)
    private Integer systemUserId;

    @JsonView(JsonViews.Resumen.class)
    private Integer permissionId;

    @JsonView(JsonViews.Detalle.class)
    private Instant createdAt;

    @JsonView(JsonViews.Detalle.class)
    private Instant updatedAt;
}
