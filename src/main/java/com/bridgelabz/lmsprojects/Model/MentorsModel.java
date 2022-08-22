package com.bridgelabz.lmsprojects.Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bridgelabz.lmsprojects.Dto.MentorsDto;

import com.bridgelabz.lmsprojects.Dto.MentorsDto;
import lombok.Data;
@Entity(name="mentors_model")
@Data

public class MentorsModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long mentorsId;
	private String employeeId;
	private String firstName;
	private String lastName;
	private String mentorType;
	private String mentorRole;
	private String mobileNumber;
	private String email;
	private String experienceYears;
	private String preferredTime;
	private LocalDate startDate;
	private String status;
	private String mentorDescription;
	private String profileImageURL;
	private int creatorUser;
	private long supervisorId;
	
	public MentorsModel(MentorsDto mentorsDto) {
		this.creatorUser=mentorsDto.getCreatorUser();
		this.email=mentorsDto.getEmail();
		this.employeeId=mentorsDto.getEmployeeId();
		this.experienceYears=mentorsDto.getExperienceYears();
		this.firstName=mentorsDto.getFirstName();
		this.lastName=mentorsDto.getLastName();
		this.mentorDescription=mentorsDto.getMentorDescription();
		this.mentorRole=mentorsDto.getMentorRole();
		this.mentorType=mentorsDto.getMentorType();
		this.mobileNumber=mentorsDto.getMobileNumber();
		this.preferredTime=mentorsDto.getPreferredTime();
		this.profileImageURL=mentorsDto.getProfileImageURL();
		this.startDate=mentorsDto.getStartDate();
		this.status=mentorsDto.getStatus();
		this.supervisorId=mentorsDto.getSupervisorId();
	}

	public MentorsModel() {
		super();
	}
	
	

}
