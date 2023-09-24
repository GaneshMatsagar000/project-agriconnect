package com.agri.agriculture.service;

import java.util.List;

import com.agri.agriculture.dto.VillageDTO;

public interface VillageService {
    List<VillageDTO> getAllVillages();
    VillageDTO getVillageById(Long id);
    VillageDTO createVillage(VillageDTO villageDTO);
    VillageDTO updateVillage(Long id, VillageDTO villageDTO);
    boolean deleteVillage(Long id);
}
