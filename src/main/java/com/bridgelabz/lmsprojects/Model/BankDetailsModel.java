package com.bridgelabz.lmsprojects.Model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bridgelabz.lmsprojects.Dto.BankDetailsDto;

import lombok.Data;

@Entity
@Data
public class BankDetailsModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String Accountno;
	private String Ifsccode;
	private String Branch;
	private String linkedMobNo;
	private String AccountHolderName;
	@OneToOne
	AdminModel CreatorUser;
	@OneToOne
	AdminModel UpdatedUser;
	@CreationTimestamp
	private LocalDateTime CreatorDateTime;
	@UpdateTimestamp
	private LocalDateTime updatedDateTime;

	public BankDetailsModel(BankDetailsDto bankDetailsDto) {
		this.AccountHolderName=bankDetailsDto.getAccountHolderName();
		this.Accountno=bankDetailsDto.getAccountno();
		this.Branch=bankDetailsDto.getBranch();
		this.Ifsccode=bankDetailsDto.getIfsccode();
		this.linkedMobNo=bankDetailsDto.getLinkedMobNo();
		
	}

	public BankDetailsModel() {
		super();
	}
	
}
