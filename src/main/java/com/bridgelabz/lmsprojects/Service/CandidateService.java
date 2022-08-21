package com.bridgelabz.lmsprojects.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.lmsprojects.Dto.CandidateDto;
import com.bridgelabz.lmsprojects.Exception.ContentNotFoundException;
import com.bridgelabz.lmsprojects.Model.AdminModel;
import com.bridgelabz.lmsprojects.Model.CandidateModel;
import com.bridgelabz.lmsprojects.Repository.IAdminRepository;
import com.bridgelabz.lmsprojects.Repository.ICandidateRepository;
import com.bridgelabz.lmsprojects.Util.StatusCount;
import com.bridgelabz.lmsprojects.Util.TokenUtil;

@Service
public class CandidateService implements ICandidateService{
	
	@Autowired
	ICandidateRepository candidateRepo;
	
	@Autowired
	TokenUtil tokenUtil;

	@Autowired 
	IAdminRepository adminRepo;
	
	@Override
	public CandidateModel create(String token, CandidateDto candidateDto) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if(isAdminPresent.isPresent()) {
			CandidateModel candidateModel = new CandidateModel(candidateDto);
			candidateRepo.save(candidateModel);
			return candidateModel;
		}
		else
			throw new ContentNotFoundException("Authentication Failed");
	}

	@Override
	public List<CandidateModel> read(String token) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if(isAdminPresent.isPresent()) {
			List<CandidateModel> list = candidateRepo.findAll();
			return list;
		}
		throw new ContentNotFoundException("Authentication Failed");
	}

	@Override
	public CandidateModel update(String token,long candidateId, CandidateDto candidateDto) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if(isAdminPresent.isPresent()) {
			Optional<CandidateModel> isCandidatePresent = candidateRepo.findById(candidateId);
			if(isCandidatePresent.isPresent()) {
				isCandidatePresent.get().setAggrPer(candidateDto.getAggrPer());
				isCandidatePresent.get().setCandidateStatus(candidateDto.getCandidateStatus());
				isCandidatePresent.get().setCicId(candidateDto.getCicId());
				isCandidatePresent.get().setCity(candidateDto.getCity());
				isCandidatePresent.get().setCreatorUser(candidateDto.getCreatorUser());
				isCandidatePresent.get().setDegree(candidateDto.getDegree());
				isCandidatePresent.get().setEmail(candidateDto.getEmail());
				isCandidatePresent.get().setFullName(candidateDto.getFullName());
				isCandidatePresent.get().setHiredDate(candidateDto.getHiredDate());
				isCandidatePresent.get().setMobileNum(candidateDto.getMobileNum());
				isCandidatePresent.get().setPassedOutYear(candidateDto.getPassedOutYear());
				isCandidatePresent.get().setPreferredJobLocation(candidateDto.getPreferredJobLocation());
				isCandidatePresent.get().setState(candidateDto.getState());
				isCandidatePresent.get().setStatus(candidateDto.getStatus());
				candidateRepo.save(isCandidatePresent.get());
				return isCandidatePresent.get();
			}
			else
				throw new ContentNotFoundException("Candidate not present");
		}
		throw new ContentNotFoundException("Authentaction Failed");
	}

	@Override
	public CandidateModel delete(String token, long candidateId) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if(isAdminPresent.isPresent()) {
			Optional<CandidateModel> isCandidatePresent = candidateRepo.findById(candidateId);
			if(isCandidatePresent.isPresent()) {
				candidateRepo.delete(isCandidatePresent.get());
				return isCandidatePresent.get();
			}
			else
				throw new ContentNotFoundException("Candidate not present");				
		}
		else
			throw new ContentNotFoundException("Authentaction Failed");
	}

	@Override
	public List<CandidateModel> getByStatus(String token, String status) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if(isAdminPresent.isPresent()) {
			List<CandidateModel> listbystatus = candidateRepo.findAllByStatus(status);
			return listbystatus;
		}
		throw new ContentNotFoundException("Authentaction Failed");
	}

	@Override
	public CandidateModel changeStatus(String token, long candidateId, String newStatus) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if(isAdminPresent.isPresent()) {
			Optional<CandidateModel> isCandidatePresent = candidateRepo.findById(candidateId);
			if(isCandidatePresent.isPresent()) {
				isCandidatePresent.get().setStatus(newStatus);
				candidateRepo.save(isCandidatePresent.get());
				return isCandidatePresent.get();
			}
			else
				throw new ContentNotFoundException("Candidate not present");
		}
		else
			throw new ContentNotFoundException("Authentaction Failed");
	}

	@Override
	public StatusCount getCountByStatus(String token) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if(isAdminPresent.isPresent()) {
			long inProgressCount = candidateRepo.getCountByStatus("In Progress");
			long completedCount = candidateRepo.getCountByStatus("Completed");
			long droppedCount = candidateRepo.getCountByStatus("Dropped");
			long remappedCount = candidateRepo.getCountByStatus("Remapped");
			return new StatusCount(inProgressCount,completedCount,droppedCount,remappedCount);
		}
		else
			throw new ContentNotFoundException("Authentaction Failed");
	}
	
	

}