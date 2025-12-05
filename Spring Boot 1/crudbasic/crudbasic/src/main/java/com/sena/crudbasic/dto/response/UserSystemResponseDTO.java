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
public class UserSystemResponseDTO {

    @JsonView(JsonViews.Resumen.class)
    private Integer id;

    @JsonView(JsonViews.Resumen.class)
    private String loginName;

    @JsonView(JsonViews.Detalle.class)
    private Instant createdAt;

    @JsonView(JsonViews.Detalle.class)
    private Instant updatedAt;
}
