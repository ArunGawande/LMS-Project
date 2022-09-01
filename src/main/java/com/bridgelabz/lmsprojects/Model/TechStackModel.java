package com.bridgelabz.lmsprojects.Model;

import java.time.LocalDateTime;

import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import com.bridgelabz.lmsprojects.Dto.TechStackDto;

import lombok.Data;

@Entity(name="tech_stack")
@Data
public class TechStackModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@CreationTimestamp
	private LocalDateTime creatorStamp;
	@OneToOne
	private AdminModel creatorUser;
	private String imagePath;
	private String status;
	private String techName;

	public TechStackModel(TechStackDto techStackDto) {
		this.imagePath=techStackDto.getImagePath();
		this.status=techStackDto.getStatus();
		this.techName=techStackDto.getTechName();
	}

	public TechStackModel() {
		super();
	}

}
