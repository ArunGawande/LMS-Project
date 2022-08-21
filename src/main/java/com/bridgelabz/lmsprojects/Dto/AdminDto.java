package com.bridgelabz.lmsprojects.Dto;

import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class AdminDto {

	@Pattern(regexp="^[A-Z]{1}[a-zA-Z\\s]{2,}$",message="first name invalid  !!!!!!")
	private String firstName;
	@Pattern(regexp="^[A-Z]{1}[a-zA-Z\\s]{2,}$",message="last name invalid  !!!!!!")
	private String lastName;
	private String mobile;
	@Pattern(regexp="^[a-zA-Z]+([_+-.][a-zA-Z])*[@][a-zA-Z]+[.][a-z]{2,3}([.][a-zA-Z]{2})*$",message="Invalid mail id ...!!!")
	private String emailId;
	private String password;
	private String profilePath;
	private String status;
	
}
