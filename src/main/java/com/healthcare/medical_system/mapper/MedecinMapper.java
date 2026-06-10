package com.healthcare.medical_system.mapper;

import com.healthcare.medical_system.dto.MedecinDTO;
import com.healthcare.medical_system.entity.Medecin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedecinMapper {

    @Mapping(target = "user", ignore = true)
    Medecin toEntity(MedecinDTO medecinDTO);

    @Mapping(source = "user.id", target = "userId")
    MedecinDTO toDTO(Medecin medecin);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntityFromDTO(MedecinDTO medecinDTO, @MappingTarget Medecin medecin);
    List<MedecinDTO> toDtoList(List<Medecin> medecins);
}
