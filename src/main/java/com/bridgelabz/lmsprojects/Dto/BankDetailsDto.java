package com.bridgelabz.lmsprojects.Dto;

import javax.persistence.OneToOne;
import com.bridgelabz.lmsprojects.Model.AdminModel;

import lombok.Data;

@Data
public class BankDetailsDto {
	
	private String Accountno;
	private String Ifsccode;
	private String Branch;
	private String linkedMobNo;
	private String AccountHolderName;
	
}
