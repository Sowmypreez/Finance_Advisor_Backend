package com.finance.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.finance.dto.UserInputDTO;

@Service
public class ValidateInput {

	public boolean validateInputInfo(UserInputDTO inputDTO) {
		if (inputDTO.getBonds() != null &&  StringUtils.isNotBlank(inputDTO.getBonds()) && 
				inputDTO.getRiskID() != null && StringUtils.isNotBlank(inputDTO.getRiskID()) && 
				inputDTO.getForeign() != null && StringUtils.isNotBlank(inputDTO.getForeign()) && 
				inputDTO.getMidCap() != null  && StringUtils.isNotBlank(inputDTO.getMidCap()) ) {
			return true;
		} else {
			return false;
		}
	}

	
}
