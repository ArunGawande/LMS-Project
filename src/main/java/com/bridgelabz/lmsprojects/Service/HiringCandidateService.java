package com.bridgelabz.lmsprojects.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.lmsprojects.Dto.HiringCandidateDto;
import com.bridgelabz.lmsprojects.Exception.ContentNotFoundException;
import com.bridgelabz.lmsprojects.Model.AdminModel;
import com.bridgelabz.lmsprojects.Model.BankDetailsModel;
import com.bridgelabz.lmsprojects.Model.CandidateModel;
import com.bridgelabz.lmsprojects.Model.HiringCandidateModel;
import com.bridgelabz.lmsprojects.Repository.IAdminRepository;
import com.bridgelabz.lmsprojects.Repository.IBankDetailsRepository;
import com.bridgelabz.lmsprojects.Repository.IHiringCandidateRepository;
import com.bridgelabz.lmsprojects.Util.TokenUtil;

@Service
public class HiringCandidateService implements IHiringCandidateService {

	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	IAdminRepository adminRepo;

	@Autowired
	IHiringCandidateRepository hiringCandidateRepo;
	
	@Autowired
	IBankDetailsRepository bankRepo;

	@Override
	public HiringCandidateModel create(String token, HiringCandidateDto hiringCandidateDto) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if (isAdminPresent.isPresent()) {
			HiringCandidateModel hiringCandidateModel = new HiringCandidateModel(hiringCandidateDto);
			hiringCandidateRepo.save(hiringCandidateModel);
			return hiringCandidateModel;
		} else
			throw new ContentNotFoundException("Authentication Failed");
	}

	@Override
	public List<HiringCandidateModel> read(String token) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if (isAdminPresent.isPresent()) {
			List<HiringCandidateModel> list = hiringCandidateRepo.findAll();
			return list;
		} else
			throw new ContentNotFoundException("Authentication Failed");
	}

	@Override
	public HiringCandidateModel update(String token, HiringCandidateDto hiringCandidateDto, long id,long bankDetailId) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if (isAdminPresent.isPresent()) {
			Optional<HiringCandidateModel> isCandidatePresent = hiringCandidateRepo.findById(id);
			if (isCandidatePresent.isPresent()) {
				Optional<BankDetailsModel> bankDetails = bankRepo.findById(bankDetailId);
				if(bankDetails.isPresent()) {
					isCandidatePresent.get().setBankDetails(bankDetails.get());
				}
				else
					throw new ContentNotFoundException("No bank Details at id : "+bankDetailId);				
				isCandidatePresent.get().setAggrPer(hiringCandidateDto.getAggrPer());
				isCandidatePresent.get().setCandidateStatus(hiringCandidateDto.getCandidateStatus());
				isCandidatePresent.get().setCicId(hiringCandidateDto.getCicId());
				isCandidatePresent.get().setCity(hiringCandidateDto.getCity());
				isCandidatePresent.get().setCreatorUser(hiringCandidateDto.getCreatorUser());
				isCandidatePresent.get().setDegree(hiringCandidateDto.getDegree());
				isCandidatePresent.get().setEmail(hiringCandidateDto.getEmail());
				isCandidatePresent.get().setFullName(hiringCandidateDto.getFullName());
				isCandidatePresent.get().setHiredDate(hiringCandidateDto.getHiredDate());
				isCandidatePresent.get().setMobileNum(hiringCandidateDto.getMobileNum());
				isCandidatePresent.get().setPassedOutYear(hiringCandidateDto.getPassedOutYear());
				isCandidatePresent.get().setJobLocation(hiringCandidateDto.getJobLocation());
				isCandidatePresent.get().setState(hiringCandidateDto.getState());
				isCandidatePresent.get().setStatus(hiringCandidateDto.getStatus());
				hiringCandidateRepo.save(isCandidatePresent.get());
				return isCandidatePresent.get();
			} else
				throw new ContentNotFoundException("Nohiring candidate with id : " + id);
		} else
			throw new ContentNotFoundException("Authentication Failed");
	}

	@Override
	public HiringCandidateModel delete(String token, long id) {
		long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(adminId);
		if (isAdminPresent.isPresent()) {
			Optional<HiringCandidateModel> isCandidatePresent = hiringCandidateRepo.findById(id);
			if (isCandidatePresent.isPresent()) {
				hiringCandidateRepo.delete(isCandidatePresent.get());
				return isCandidatePresent.get();
			} else
				throw new ContentNotFoundException("No Candidate with id : " + id);
		} else
			throw new ContentNotFoundException("Authentication Failed");
	}

}
