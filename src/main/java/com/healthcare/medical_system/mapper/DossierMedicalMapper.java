package com.healthcare.medical_system.mapper;

import com.healthcare.medical_system.dto.DossierMedicalDTO;
import com.healthcare.medical_system.dto.PatientDTO;
import com.healthcare.medical_system.entity.DossierMedical;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DossierMedicalMapper {

    @Mapping(source = "patientId", target = "patient.id")
    DossierMedical toEntity(DossierMedicalDTO dossierMedicalDTO);

    @Mapping(source = "patient.id", target = "patientId")
    DossierMedicalDTO toDTO(DossierMedical dossierMedical);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "patientId", target = "patient.id")
    void updateEntityFromDto(DossierMedicalDTO dossierMedicalDTO, @MappingTarget DossierMedical dossierMedical);

    List<DossierMedicalDTO> toDtoList(List<DossierMedical> dossierMedicals);
}
