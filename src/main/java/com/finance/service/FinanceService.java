package com.finance.service;

import org.springframework.stereotype.Service;
import com.finance.dto.StatusResponseDTO;
import com.finance.dto.UserInputDTO;


@Service
public interface FinanceService {
	public StatusResponseDTO calculateRisk(UserInputDTO userRegisterDTO) throws Exception;
}
