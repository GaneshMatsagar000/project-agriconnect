package com.agri.agriculture.service;

import com.agri.agriculture.dto.DriverDTO;
import com.agri.agriculture.entity.Driver;

public interface DriverService {
    DriverDTO getDriverById(Long id);
    // Other driver-related methods
	Driver registerDriver(Driver driver);
}
