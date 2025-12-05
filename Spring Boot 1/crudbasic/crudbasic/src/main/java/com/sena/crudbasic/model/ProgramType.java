package com.sena.crudbasic.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "program_type")
public class ProgramType extends BaseEntity {

    @Column(name = "type_name", nullable = false)
    private String typeName;

    @OneToMany(mappedBy = "programType")
    private List<ModuleAssignment> modules;

}
