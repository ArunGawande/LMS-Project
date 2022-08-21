package com.bridgelabz.lmsprojects.Model;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.bridgelabz.lmsprojects.Dto.CandidateDto;
import lombok.Data;


@Entity
@Table(name = "CandidateModel")
@Data

public class CandidateModel {

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
	private String preferredJobLocation;
	private String status;
	private String passedOutYear;
	private String creatorUser;
	private String candidateStatus;
	@CreationTimestamp
	private LocalDateTime creationTimeStamp;
	@UpdateTimestamp
	private LocalDateTime updatedTimeStamp;
	
	public CandidateModel(CandidateDto candidateDto) {
		this.cicId=candidateDto.getCicId();
		this.fullName=candidateDto.getFullName();
		this.email=candidateDto.getEmail();
		this.mobileNum=candidateDto.getMobileNum();
		this.hiredDate=candidateDto.getHiredDate();
		this.degree=candidateDto.getDegree();
		this.aggrPer=candidateDto.getAggrPer();
		this.city=candidateDto.getCity();
		this.state=candidateDto.getState();
		this.preferredJobLocation=candidateDto.getPreferredJobLocation();
		this.status=candidateDto.getStatus();
		this.passedOutYear=candidateDto.getPassedOutYear();
		this.creatorUser=candidateDto.getCreatorUser();
		this.candidateStatus=candidateDto.getCandidateStatus();
	}

	public CandidateModel() {
		super();
	}
	
	
}
