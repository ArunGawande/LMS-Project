package com.bridgelabz.lmsprojects.Model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bridgelabz.lmsprojects.Dto.HiringCandidateDto;

import lombok.Data;

@Entity(name = "hiring_candidate")
@Data
public class HiringCandidateModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String cicId;
	private String fullName;
	private String email;
	private String mobileNum;
	private String hiredDate;
	private String degree;
	private Double aggrPer;
	private String city;
	private String state;
	private String JobLocation;
	private String status;
	private String passedOutYear;
	private String creatorUser;
	private String candidateStatus;
	@CreationTimestamp
	private LocalDateTime creatorStamp;
	@UpdateTimestamp
	private LocalDateTime updatedStamp;
	@OneToOne
	BankDetailsModel bankDetails;

	public HiringCandidateModel(HiringCandidateDto hiringCandidateDto) {
		this.aggrPer = hiringCandidateDto.getAggrPer();
		this.candidateStatus = hiringCandidateDto.getCandidateStatus();
		this.cicId = hiringCandidateDto.getCicId();
		this.city = hiringCandidateDto.getCity();
		this.creatorUser = hiringCandidateDto.getCreatorUser();
		this.degree = hiringCandidateDto.getDegree();
		this.email = hiringCandidateDto.getEmail();
		this.fullName = hiringCandidateDto.getFullName();
		this.hiredDate = hiringCandidateDto.getHiredDate();
		this.JobLocation = hiringCandidateDto.getJobLocation();
		this.mobileNum = hiringCandidateDto.getMobileNum();
		this.passedOutYear = hiringCandidateDto.getPassedOutYear();
		this.state = hiringCandidateDto.getState();
		this.status = hiringCandidateDto.getStatus();
	}

	public HiringCandidateModel() {
		super();
	}
	
	
}
