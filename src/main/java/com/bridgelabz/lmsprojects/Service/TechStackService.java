package com.bridgelabz.lmsprojects.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.lmsprojects.Dto.TechStackDto;
import com.bridgelabz.lmsprojects.Exception.ContentNotFoundException;
import com.bridgelabz.lmsprojects.Model.AdminModel;
import com.bridgelabz.lmsprojects.Model.TechStackModel;
import com.bridgelabz.lmsprojects.Repository.IAdminRepository;
import com.bridgelabz.lmsprojects.Repository.ITechStackRepository;
import com.bridgelabz.lmsprojects.Util.TokenUtil;

@Service
public class TechStackService implements ITechStackService{
	
	@Autowired
	TokenUtil tokenUtil;
	
	@Autowired
	IAdminRepository adminRepo;
	
	@Autowired
	ITechStackRepository techRepo;

	@Override
	public TechStackModel create(String token, TechStackDto techStackDto,long creatorUserId) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if(isAdminPresent.isPresent()) {
			Optional<AdminModel> creator = adminRepo.findById(creatorUserId);
			if(creator.isPresent()) {
				TechStackModel techStackModel = new TechStackModel(techStackDto);
				techStackModel.setCreatorUser(creator.get());
				techRepo.save(techStackModel);
				return techStackModel;				
			}
			else
				throw new ContentNotFoundException("No Creator user present at id: "+ creatorUserId);
		}
		else
			throw new ContentNotFoundException("Authentication Failed");
	}

	@Override
	public List<TechStackModel> read(String token) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if(isAdminPresent.isPresent()) {
			List<TechStackModel> list = techRepo.findAll();
			if(!list.isEmpty())
				return list;
			else
				throw new ContentNotFoundException("List is Empty !!!!");
		}
		else
			throw new ContentNotFoundException("Authentication Failed");

	}

	@Override
	public TechStackModel update(String token,TechStackDto techStackDto, long techStackId, long creatorUserId) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if(isAdminPresent.isPresent()) {
			Optional<TechStackModel> techStack = techRepo.findById(techStackId);
			if(techStack.isPresent()) {
				Optional<AdminModel> creator = adminRepo.findById(creatorUserId);
				if(creator.isPresent())
				techStack.get().setCreatorUser(creator.get());
				else
					throw new ContentNotFoundException("creator Not present witid : "+creatorUserId);
				techStack.get().setImagePath(techStackDto.getImagePath());
				techStack.get().setStatus(techStackDto.getStatus());
				techStack.get().setTechName(techStackDto.getTechName());
				techRepo.save(techStack.get());
				return techStack.get();
			}
			else
				throw new ContentNotFoundException("Tech stack not available with id : "+techStackId);
		}
		else
			throw new ContentNotFoundException("Authentication Failed");
	}

	@Override
	public TechStackModel delete(String token, long id) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if(isAdminPresent.isPresent()) {
			Optional<TechStackModel> isTechStackPresent =techRepo.findById(id);
			if(isTechStackPresent.isPresent()) {
				techRepo.delete(isTechStackPresent.get());
				return isTechStackPresent.get();
			}
			else
				throw new ContentNotFoundException("No data Present with id : "+ id);
		}
		else
			throw new ContentNotFoundException("Authentication Failed");
	}

	
	

}
