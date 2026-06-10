package com.healthcare.medical_system.mapper;


import com.healthcare.medical_system.dto.PatientDTO;
import com.healthcare.medical_system.entity.Medecin;
import com.healthcare.medical_system.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(target = "user", ignore = true)
    Patient  toEntity(PatientDTO patientDTO);

    @Mapping(source = "user.id", target = "userId")
    PatientDTO toDTO(Patient patient);
    @Mapping(target = "id", ignore = true)

    @Mapping(target = "user", ignore = true)
    void updateEntityFromDTO (PatientDTO patientDTO, @MappingTarget Patient patient);
    List<PatientDTO> toDtoList(List<Patient> patients);
}
