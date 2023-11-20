package com.agri.agriculture.serviceimpl;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agri.agriculture.dto.StateDTO;
import com.agri.agriculture.entity.State;
import com.agri.agriculture.repository.StateRepository;
import com.agri.agriculture.service.StateService;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateRepository stateRepository;

    @Override
    public List<StateDTO> getAllStates() {
        List<State> states = stateRepository.findAll();
        return states.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public StateDTO getStateById(Long id) {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("State not found with id: " + id));
        return mapToDTO(state);
    }

    @Override
    public StateDTO createState(StateDTO stateDTO) {
        State state = mapToEntity(stateDTO);
        State savedState = stateRepository.save(state);
        return mapToDTO(savedState);
    }

    @Override
    public StateDTO updateState(Long id, StateDTO stateDTO) {
        State existingState = stateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("State not found with id: " + id));

        // Update the fields of existingState using data from stateDTO
        existingState.setName(stateDTO.getName());
        // Update other fields as needed

        State updatedState = stateRepository.save(existingState);
        return mapToDTO(updatedState);
    }

    @Override
    public boolean deleteState(Long id) {
        try {
            State state = stateRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("State not found with id: " + id));
            stateRepository.delete(state);
            return true; // Deletion successful
        } catch (Exception e) {
            return false; // Deletion failed
        }
    }

    // Helper methods to map between Entity and DTO
    private StateDTO mapToDTO(State state) {
        StateDTO dto = new StateDTO();
        dto.setId(state.getId());
        dto.setName(state.getName());
        // Map other fields as needed
        return dto;
    }

    private State mapToEntity(StateDTO stateDTO) {
        State state = new State();
        state.setName(stateDTO.getName());
        // Map other fields as needed
        return state;
    }
}
