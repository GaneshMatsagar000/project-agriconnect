package com.agri.agriculture.service;

import java.util.List;

import com.agri.agriculture.dto.StateDTO;

public interface StateService {
    List<StateDTO> getAllStates();
    StateDTO getStateById(Long id);
    StateDTO createState(StateDTO stateDTO);
    StateDTO updateState(Long id, StateDTO stateDTO);
    boolean deleteState(Long id);
}
