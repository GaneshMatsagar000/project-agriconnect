package com.agri.agriculture.service;

import java.util.List;

import com.agri.agriculture.dto.DistrictDTO;

public interface DistrictService {
    List<DistrictDTO> getAllDistricts();
    DistrictDTO getDistrictById(Long id);
    DistrictDTO createDistrict(DistrictDTO districtDTO);
    DistrictDTO updateDistrict(Long id, DistrictDTO districtDTO);
    boolean deleteDistrict(Long id);
}
