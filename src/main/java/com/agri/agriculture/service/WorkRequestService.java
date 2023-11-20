package com.agri.agriculture.service;

import java.util.List;

import com.agri.agriculture.dto.WorkRequestDTO;
import com.agri.agriculture.entity.WorkRequest;

public interface WorkRequestService {
    void saveWorkRequest(WorkRequestDTO workRequestDTO);

	List<WorkRequest> getAllWorkRequst();
}
