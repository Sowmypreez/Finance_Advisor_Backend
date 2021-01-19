package com.finance.service.impl;

import java.math.BigInteger;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.finance.dto.StatusResponseDTO;
import com.finance.dto.UserInputDTO;
import com.finance.service.FinanceService;

@Service
public class FinanceServiceImpl implements FinanceService{

	@Override
	public StatusResponseDTO calculateRisk(UserInputDTO userRegisterDTO) throws Exception {
		
		
		if (userRegisterDTO.getBonds() != null && userRegisterDTO.getForeign() != null && userRegisterDTO.getLargeCap() != null  &&
				userRegisterDTO.getMidCap() != null && userRegisterDTO.getSmallCap() != null && userRegisterDTO.getRiskID() != null ) {
			
			StatusResponseDTO statusResponseDTO = new StatusResponseDTO();
			String recommended = "";
			int[][] arr = { { 80,20,0,0,0 }, { 70,15,15,0,0 }, { 60,15,15,10,0 }, {50,20,20,10,0}, {40,20,20,0,0}
			, {35,25,5,30,5}, { 20,25,25,25,5}, { 10,20,40,20,10}, {5,15,40,25,15}, { 0,5,25,30,40 }}; 
			
			int total = Integer.parseInt(userRegisterDTO.getBonds()) + Integer.parseInt(userRegisterDTO.getForeign())  +
					Integer.parseInt(userRegisterDTO.getLargeCap())  + Integer.parseInt(userRegisterDTO.getMidCap()) + Integer.parseInt(userRegisterDTO.getSmallCap()) ;
			
			int startIndex = Integer.parseInt(userRegisterDTO.getRiskID()) - 1;
			HashMap<Integer, Double> posVal = new HashMap<Integer, Double>();
			HashMap<Integer, Double> negVal = new HashMap<Integer, Double>();
			HashMap<Integer, String> riskType = new HashMap<Integer, String>();
			
			double bondVal = total * ((double)arr[startIndex][0]/100);
			double largeCap = total * ((double)arr[startIndex][1]/100);
			double midCap = total * ((double)arr[startIndex][2]/100);
			double foreign = total * ((double)arr[startIndex][3]/100);
			double smallCap = total * ((double)arr[startIndex][4]/100);
			
			double newDiffBondVal = bondVal - Integer.parseInt(userRegisterDTO.getBonds());
			double newDiffLargeCap = largeCap - Integer.parseInt(userRegisterDTO.getBonds());
			double newDiffMidCap = midCap - Integer.parseInt(userRegisterDTO.getBonds());
			double newDiffForeign = foreign - Integer.parseInt(userRegisterDTO.getBonds());
			double newDiffSmallCap = smallCap - Integer.parseInt(userRegisterDTO.getBonds());
			
			riskType.put(1, "Bonds");
			riskType.put(2, "Large Cap");
			riskType.put(3, "Mid Cap");
			riskType.put(4, "Foreign");
			riskType.put(5, "Small Cap");

			if (newDiffBondVal >= 0){posVal.put(1,newDiffBondVal); }else {negVal.put(1,newDiffBondVal); }
			if (newDiffLargeCap >= 0){posVal.put(2,newDiffLargeCap); }else {negVal.put(2,newDiffLargeCap);}
			if (newDiffMidCap >= 0){posVal.put(3,newDiffMidCap); }else {negVal.put(3,newDiffMidCap);}
			if (newDiffForeign >= 0){posVal.put(4,newDiffForeign);  }else {negVal.put(4,newDiffForeign);}
			if (newDiffSmallCap >= 0){posVal.put(5,newDiffSmallCap);  }else {negVal.put(5,newDiffSmallCap); }
			
			posVal.values().remove(0.0);negVal.values().remove(0.0);
			
			for (Integer i : negVal.keySet()) {
			    	  for (Integer j : posVal.keySet()) {
			    			  if(negVal.get(i) >= posVal.get(j)) {
			    				  recommended += "•)  Transfer " +String.valueOf(Math.abs(negVal.get(i))) + "$ from " + riskType.get(i) + " to " + riskType.get(j) + " ";
					    		  double value = Math.abs(negVal.get(i) - posVal.get(j));
					    		  negVal.replace(i, value); 
					    		  posVal.replace(j, posVal.get(j)-value); 
					    	  }else {
					    		  recommended += "•)  Transfer " +String.valueOf(Math.abs(negVal.get(i)))+ "$ from " + riskType.get(i) + " to " + riskType.get(j)+ " ";
					    	      posVal.replace(j, posVal.get(j) - Math.abs(negVal.get(i))); 
					    	      negVal.replace(i, (double) 0); 
					    	      break;
					    	  }
				      }
			    }
			
			statusResponseDTO.setBonds(String.valueOf(bondVal));
			statusResponseDTO.setLargeCap(String.valueOf(largeCap));
			statusResponseDTO.setMidCap(String.valueOf(midCap));
			statusResponseDTO.setForeign(String.valueOf(foreign));
			statusResponseDTO.setSmallCap(String.valueOf(smallCap));
			statusResponseDTO.setRecommendedTransfer(recommended);
			statusResponseDTO.setNewDiffBonds(newDiffBondVal);
			statusResponseDTO.setNewDiffLargeCap(newDiffLargeCap);
			statusResponseDTO.setNewDiffMidCap(newDiffMidCap);
			statusResponseDTO.setNewDiffForeign(newDiffForeign);
			statusResponseDTO.setNewDiffSmallCap(newDiffSmallCap);
			 
			return statusResponseDTO;
		}else {
			return null;
		}
	}

	

}
