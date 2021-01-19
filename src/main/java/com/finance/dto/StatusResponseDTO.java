package com.finance.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class StatusResponseDTO {
	private String bonds;
	private String largeCap;
	private String midCap;
	private String foreign;
	private String smallCap;
	private Double newDiffBonds;
	private Double newDiffLargeCap;
	private Double newDiffMidCap;
	private Double newDiffForeign;
	private Double newDiffSmallCap;
	private String recommendedTransfer;
	private String status;
	private String message;
	 
	public String getRecommendedTransfer() {
		return recommendedTransfer;
	}
	public void setRecommendedTransfer(String recommendedTransfer) {
		this.recommendedTransfer = recommendedTransfer;
	}
	public Double getNewDiffBonds() {
		return newDiffBonds;
	}
	public void setNewDiffBonds(Double newDiffBonds) {
		this.newDiffBonds = newDiffBonds;
	}
	public Double getNewDiffLargeCap() {
		return newDiffLargeCap;
	}
	public void setNewDiffLargeCap(Double newDiffLargeCap) {
		this.newDiffLargeCap = newDiffLargeCap;
	}
	public Double getNewDiffMidCap() {
		return newDiffMidCap;
	}
	public void setNewDiffMidCap(Double newDiffMidCap) {
		this.newDiffMidCap = newDiffMidCap;
	}
	public Double getNewDiffForeign() {
		return newDiffForeign;
	}
	public void setNewDiffForeign(Double newDiffForeign) {
		this.newDiffForeign = newDiffForeign;
	}
	public Double getNewDiffSmallCap() {
		return newDiffSmallCap;
	}
	public void setNewDiffSmallCap(Double newDiffSmallCap) {
		this.newDiffSmallCap = newDiffSmallCap;
	}
	public String getBonds() {
		return bonds;
	}
	public void setBonds(String bonds) {
		this.bonds = bonds;
	}
	public String getLargeCap() {
		return largeCap;
	}
	public void setLargeCap(String largeCap) {
		this.largeCap = largeCap;
	}
	public String getMidCap() {
		return midCap;
	}
	public void setMidCap(String midCap) {
		this.midCap = midCap;
	}
	public String getForeign() {
		return foreign;
	}
	public void setForeign(String foreign) {
		this.foreign = foreign;
	}
	public String getSmallCap() {
		return smallCap;
	}
	public void setSmallCap(String smallCap) {
		this.smallCap = smallCap;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
