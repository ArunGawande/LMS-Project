package com.bridgelabz.lmsprojects.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.lmsprojects.Dto.MentorsDto;
import com.bridgelabz.lmsprojects.Exception.ContentNotFoundException;
import com.bridgelabz.lmsprojects.Model.AdminModel;
import com.bridgelabz.lmsprojects.Model.MentorsModel;
import com.bridgelabz.lmsprojects.Repository.IAdminRepository;
import com.bridgelabz.lmsprojects.Repository.IMentorsRepository;
import com.bridgelabz.lmsprojects.Util.TokenUtil;

@Service
public class MentorsService implements IMentorsService{
	
	@Autowired 
	IMentorsRepository mentorsRepo;
	@Autowired
	IAdminRepository adminRepo;
	@Autowired
	TokenUtil tokenUtil;

	@Override
	public MentorsModel create(String token, MentorsDto mentorsDto) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if(isAdminPresent.isPresent()) {
			MentorsModel mentorsModel = new MentorsModel(mentorsDto); 
			mentorsRepo.save(mentorsModel);
			return mentorsModel;
		}
		else
			throw new ContentNotFoundException("Authentication Failed");
	}

	@Override
	public List<MentorsModel> read(String token) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if(isAdminPresent.isPresent()) {
			List<MentorsModel> list = mentorsRepo.findAll();
			if(!list.isEmpty()) {				
				return list;
			}
			else
				throw new ContentNotFoundException("No Data Present");
		}
		else
			throw new ContentNotFoundException("Authentication Failed");
	}

	@Override
	public MentorsModel update(String token, MentorsDto mentorsDto,long mentorsId) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if(isAdminPresent.isPresent()) {
			Optional<MentorsModel> isMentorPresent = mentorsRepo.findById(mentorsId);
			if(isMentorPresent.isPresent()) {
				isMentorPresent.get().setCreatorUser(mentorsDto.getCreatorUser());
				isMentorPresent.get().setEmail(mentorsDto.getEmail());
				isMentorPresent.get().setEmployeeId(mentorsDto.getEmployeeId());
				isMentorPresent.get().setExperienceYears(mentorsDto.getExperienceYears());
				isMentorPresent.get().setFirstName(mentorsDto.getFirstName());
				isMentorPresent.get().setLastName(mentorsDto.getLastName());
				isMentorPresent.get().setMentorDescription(mentorsDto.getMentorDescription());
				isMentorPresent.get().setMentorRole(mentorsDto.getMentorRole());
				isMentorPresent.get().setMentorType(mentorsDto.getMentorType());
				isMentorPresent.get().setMobileNumber(mentorsDto.getMobileNumber());
				isMentorPresent.get().setPreferredTime(mentorsDto.getPreferredTime());
				isMentorPresent.get().setProfileImageURL(mentorsDto.getProfileImageURL());
				isMentorPresent.get().setStartDate(mentorsDto.getStartDate());
				isMentorPresent.get().setStatus(mentorsDto.getStatus());
				isMentorPresent.get().setSupervisorId(mentorsDto.getSupervisorId());
				mentorsRepo.save(isMentorPresent.get());
				return isMentorPresent.get();
			}
			else
				throw new ContentNotFoundException("No mentor with id : "+ mentorsId);
		}
		else
			throw new ContentNotFoundException("Authentication Failed");
	}

	@Override
	public MentorsModel delete(String token,long mentorsId) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if(isAdminPresent.isPresent()) {
			Optional<MentorsModel> isMentorPresent = mentorsRepo.findById(mentorsId);
			if(isMentorPresent.isPresent()) {
				mentorsRepo.delete(isMentorPresent.get());
				return isMentorPresent.get();
			}
			else
				throw new ContentNotFoundException("No mentor with id : "+ mentorsId);
		}
		else
			throw new ContentNotFoundException("Authentication Failed");
	}

	@Override
	public Map<String, Long> getRolesCounts(String token) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if(isAdminPresent.isPresent()) {
			Map<String,Long> mentors = new HashMap<>();
			List<MentorsModel> allMentors = mentorsRepo.findAll();
			allMentors.forEach((x)->{
				String role =x.getMentorRole();
				long count = mentorsRepo.getCountByRole(role);
				if(!mentors.containsKey(role)) 
					mentors.put(role,count);
			});
			return mentors;
		}
		else
			throw new ContentNotFoundException("Authentication Failed");
	}

	@Override
	public MentorsModel fetchDetail(String token, long mentorId) {
		
		return null;
	}
	
	

}
