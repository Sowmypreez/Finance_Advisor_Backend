package com.finance.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.finance.dto.StatusResponseDTO;
import com.finance.dto.UserInputDTO;
import com.finance.service.FinanceService;
import com.finance.utils.ValidateInput;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/")
@CrossOrigin
public class ActivityController {

	private static final Logger LOG = LoggerFactory.getLogger(ActivityController.class);

	@Autowired
	private Environment env;
	
	@Autowired
	ValidateInput validateInput;
	
	@Autowired
	FinanceService financeService;
	
	

	@CrossOrigin
	@RequestMapping(value = "/calculate", method = RequestMethod.POST, produces = { "application/json" })
	@ApiOperation(value = "calculate value", notes = "Need to calculate value")
	public synchronized ResponseEntity<String> createWallet(
			@ApiParam(value = "Calculate value for users", required = true) @RequestBody UserInputDTO inputDTO)
			throws Exception {

		StatusResponseDTO statusResponseDTO = new StatusResponseDTO();
		try {

			// User Input validation failed
			boolean isValid = validateInput.validateInputInfo(inputDTO);
			if (!isValid) {
				statusResponseDTO.setStatus(env.getProperty("failure"));
				statusResponseDTO.setMessage(env.getProperty("incorrectDetails"));
				return new ResponseEntity<String>(new Gson().toJson(statusResponseDTO), HttpStatus.PARTIAL_CONTENT);
			}

			// Calculate Risk Details
			StatusResponseDTO statusDTO = financeService.calculateRisk(inputDTO);
			if (statusDTO != null) {
				statusResponseDTO.setBonds(statusDTO.getBonds());
				statusResponseDTO.setForeign(statusDTO.getForeign());
				statusResponseDTO.setLargeCap(statusDTO.getLargeCap());
				statusResponseDTO.setMidCap(statusDTO.getMidCap());
				statusResponseDTO.setSmallCap(statusDTO.getSmallCap());
				statusResponseDTO.setNewDiffBonds(statusDTO.getNewDiffBonds());
				statusResponseDTO.setNewDiffForeign(statusDTO.getNewDiffForeign());
				statusResponseDTO.setNewDiffLargeCap(statusDTO.getNewDiffLargeCap());
				statusResponseDTO.setNewDiffMidCap(statusDTO.getNewDiffMidCap());
				statusResponseDTO.setNewDiffSmallCap(statusDTO.getNewDiffSmallCap());
				statusResponseDTO.setRecommendedTransfer(statusDTO.getRecommendedTransfer());
				statusResponseDTO.setStatus(env.getProperty("success"));
				statusResponseDTO.setMessage(env.getProperty("calculate.success"));
				return new ResponseEntity<String>(new Gson().toJson(statusResponseDTO), HttpStatus.OK);
			} else {
				statusResponseDTO.setStatus(env.getProperty("failure"));
				statusResponseDTO.setMessage(env.getProperty("calculate.failed"));
				return new ResponseEntity<String>(new Gson().toJson(statusResponseDTO), HttpStatus.PARTIAL_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			statusResponseDTO.setStatus(env.getProperty("failure"));
			statusResponseDTO.setMessage(env.getProperty("server.problem"));
			return new ResponseEntity<String>(new Gson().toJson(statusResponseDTO), HttpStatus.PARTIAL_CONTENT);
		}
	}

}
