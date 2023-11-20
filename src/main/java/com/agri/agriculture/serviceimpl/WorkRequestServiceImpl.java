package com.agri.agriculture.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agri.agriculture.dto.WorkRequestDTO;
import com.agri.agriculture.entity.WorkRequest;
import com.agri.agriculture.repository.WorkRequestRepository;
import com.agri.agriculture.service.WorkRequestService;

@Service
public class WorkRequestServiceImpl implements WorkRequestService {

    private final WorkRequestRepository workRequestRepository;

    @Autowired
    public WorkRequestServiceImpl(WorkRequestRepository workRequestRepository) {
        this.workRequestRepository = workRequestRepository;
    }

    @Override
    public void saveWorkRequest(WorkRequestDTO workRequestDTO) {
        // Convert WorkRequestDTO to WorkRequest entity
        WorkRequest workRequest = new WorkRequest();
        workRequest.setFarmerName(workRequestDTO.getFarmerName());
        workRequest.setPhoneNumber(workRequestDTO.getPhoneNumber());
        workRequest.setFarmAddress(workRequestDTO.getFarmAddress());
        workRequest.setWorkDetails(workRequestDTO.getWorkDetails());
        workRequest.setOtherWorkDetails(workRequestDTO.getOtherWorkDetails());
        workRequest.setFieldArea(workRequestDTO.getFieldArea());
        workRequest.setOtherFieldArea(workRequestDTO.getOtherFieldArea());
        workRequest.setTractorNeed(workRequestDTO.getTractorNeed());
        workRequest.setPaymentMode(workRequestDTO.getPaymentMode());
        workRequest.setLatitude(workRequestDTO.getLatitude());
        workRequest.setLongitude(workRequestDTO.getLongitude());

        // Save the entity to the database
        workRequestRepository.save(workRequest);
    }

	

	@Override
	public List<WorkRequest> getAllWorkRequst() {
		return (List<WorkRequest>) workRequestRepository.findAll();
	}
	
	
}
