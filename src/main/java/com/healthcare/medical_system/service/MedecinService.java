package com.healthcare.medical_system.service;

import com.healthcare.medical_system.dto.MedecinDTO;
import com.healthcare.medical_system.entity.Medecin;
import com.healthcare.medical_system.mapper.MedecinMapper;
import com.healthcare.medical_system.repository.MedecinRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedecinService {
    private final MedecinRepository medecinRepository;
    private final MedecinMapper medecinMapper;

    @Transactional
    public MedecinDTO ajouterMedecin(MedecinDTO medecinDTO){
        Medecin medecin = medecinMapper.toEntity(medecinDTO);
        Medecin savedMedecin = medecinRepository.save(medecin);
        return medecinMapper.toDTO(savedMedecin);
    }

    @Transactional
    public MedecinDTO modifierMedecin(Long id, MedecinDTO medecinDTO){
        Medecin medecin = medecinRepository.findById(id).orElseThrow(()->new RuntimeException("medecin non trouvé"));
        medecinMapper.updateEntityFromDTO(medecinDTO, medecin);
        Medecin updatedMedecin = medecinRepository.save(medecin);
        return medecinMapper.toDTO(updatedMedecin);
    }

    @Transactional
    public void supprimerMedecin (Long id){
        Medecin medecin = medecinRepository.findById(id).orElseThrow(()-> new RuntimeException("medecin non trouvé"));
        medecinRepository.delete(medecin);
    }

    @Transactional
    public List<MedecinDTO> listerMedecins(){
        List<Medecin> medecins = medecinRepository.findAll();
        return medecinMapper.toDtoList(medecins);
    }

}
