package com.healthcare.medical_system.mapper;

import com.healthcare.medical_system.dto.PatientDTO;
import com.healthcare.medical_system.dto.RendezVousDTO;
import com.healthcare.medical_system.entity.RendezVous;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RendezVousMapper {

    @Mapping(source = "patientId", target="patient.id")
    @Mapping(source = "medecinId", target="medecin.id")
    RendezVous toEntity(RendezVousDTO rendezVousDTO);

    @Mapping(source="patient.id", target="patientId")
    @Mapping(source="medecin.id", target="medecinId")
    RendezVousDTO toDTO(RendezVous rendezVous);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "patientId", target="patient.id")
    @Mapping(source = "medecinId", target="medecin.id")
    void updateEntityFromDto(RendezVousDTO rendezVousDTO, @MappingTarget RendezVous rendezVous);

    List<RendezVousDTO> toDtoList(List<RendezVous> rendezVousList );
}
