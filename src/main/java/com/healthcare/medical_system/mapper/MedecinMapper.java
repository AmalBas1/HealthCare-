package com.healthcare.medical_system.mapper;

import com.healthcare.medical_system.dto.MedecinDTO;
import com.healthcare.medical_system.entity.Medecin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedecinMapper {

    Medecin toEntity(MedecinDTO medecinDTO);
    MedecinDTO toDTO(Medecin medecin);
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(MedecinDTO medecinDTO, @MappingTarget Medecin medecin);
    List<MedecinDTO> toDtoList(List<Medecin> medecins);
}
