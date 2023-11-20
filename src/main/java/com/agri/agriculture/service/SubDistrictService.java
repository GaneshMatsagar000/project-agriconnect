package com.agri.agriculture.service;

import java.util.List;

import com.agri.agriculture.dto.SubDistrictDTO;

public interface SubDistrictService {
    List<SubDistrictDTO> getAllSubDistricts();
    SubDistrictDTO getSubDistrictById(Long id);
    SubDistrictDTO createSubDistrict(SubDistrictDTO subDistrictDTO);
    SubDistrictDTO updateSubDistrict(Long id, SubDistrictDTO subDistrictDTO);
    boolean deleteSubDistrict(Long id);
}
