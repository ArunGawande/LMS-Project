package com.bridgelabz.lmsprojects.Model;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bridgelabz.lmsprojects.Dto.AdminDto;
import lombok.Data;

@Entity
@Table(name = "AdminModel")
@Data
public class AdminModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String mobile;
	private String emailId;
	private String password;
	private String profilePath;
	private String status;
	@CreationTimestamp
	private LocalDateTime creatorStamp;
	@UpdateTimestamp
	private LocalDateTime updatedStamp;

	public AdminModel(AdminDto adminDto) {
		this.firstName=adminDto.getFirstName();
		this.lastName=adminDto.getLastName();
		this.mobile=adminDto.getMobile();
		this.emailId=adminDto.getEmailId();
		this.password=adminDto.getPassword();
		this.profilePath=adminDto.getProfilePath();
		this.status=adminDto.getStatus();
	}

	public AdminModel() {
		super();
	}
}
