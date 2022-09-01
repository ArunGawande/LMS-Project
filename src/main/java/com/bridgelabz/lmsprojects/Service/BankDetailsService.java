package com.bridgelabz.lmsprojects.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.lmsprojects.Dto.BankDetailsDto;
import com.bridgelabz.lmsprojects.Exception.ContentNotFoundException;
import com.bridgelabz.lmsprojects.Model.AdminModel;
import com.bridgelabz.lmsprojects.Model.BankDetailsModel;
import com.bridgelabz.lmsprojects.Repository.IAdminRepository;
import com.bridgelabz.lmsprojects.Repository.IBankDetailsRepository;
import com.bridgelabz.lmsprojects.Util.TokenUtil;

@Service
public class BankDetailsService implements IBankDetailsService{

	@Autowired
	IBankDetailsRepository bankRepo;

	@Autowired
	TokenUtil tokenUtil;
	
	@Autowired
	IAdminRepository adminRepo;
	
	@Override
	public BankDetailsModel create(String token, BankDetailsDto bankDetailsDto,long creatorId,long updatedId) {
		long id = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(id);
		if(isAdminPresent.isPresent()) {
			BankDetailsModel bankDetailsModel = new BankDetailsModel(bankDetailsDto);
			Optional<AdminModel> isCreatorUserPresent = adminRepo.findById(creatorId);
			if(isCreatorUserPresent.isPresent()) {
				bankDetailsModel.setCreatorUser(isCreatorUserPresent.get());
			}
			else
				throw new ContentNotFoundException("Cannot add Creator User , Not present with Id: "+creatorId);

			Optional<AdminModel> isUpdatedUserPresent = adminRepo.findById(updatedId);
			if(isUpdatedUserPresent.isPresent()) {
				bankDetailsModel.setUpdatedUser(isUpdatedUserPresent.get());
			}
			else
				throw new ContentNotFoundException("Cannot add Updated User , Not present with Id: "+updatedId);

			bankRepo.save(bankDetailsModel);
			return bankDetailsModel;
		}
		else
			throw new ContentNotFoundException("Authentaction Failed");
	}

	@Override
	public List<BankDetailsModel> read(String token) {
		long id = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(id);
		if(isAdminPresent.isPresent()) {
			List<BankDetailsModel> list = bankRepo.findAll();
			return list;
		}
		else
			throw new ContentNotFoundException("Authentaction Failed");
	}

	@Override
	public BankDetailsModel update(String token, long creatorId, long updatedId,long bankDetailsId,BankDetailsDto bankDetailsDto) {
		long id = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(id);
		if(isAdminPresent.isPresent()) {
			Optional<BankDetailsModel> isBankDetailPresent = bankRepo.findById(bankDetailsId);
			if(isBankDetailPresent.isPresent()) {
				isBankDetailPresent.get().setAccountHolderName(bankDetailsDto.getAccountHolderName());
				isBankDetailPresent.get().setAccountno(bankDetailsDto.getAccountno());
				isBankDetailPresent.get().setBranch(bankDetailsDto.getBranch());
				isBankDetailPresent.get().setIfsccode(bankDetailsDto.getIfsccode());
				isBankDetailPresent.get().setLinkedMobNo(bankDetailsDto.getLinkedMobNo());
				
				Optional<AdminModel> isCreatorUserPresent = adminRepo.findById(creatorId);
				if(isCreatorUserPresent.isPresent()) {
					isBankDetailPresent.get().setCreatorUser(isCreatorUserPresent.get());
				}
				else
					throw new ContentNotFoundException("Cannot add Creator User , Not present with Id: "+creatorId);
				
				Optional<AdminModel> isUpdatedUserPresent = adminRepo.findById(updatedId);
				if(isUpdatedUserPresent.isPresent()) {
					isBankDetailPresent.get().setUpdatedUser(isUpdatedUserPresent.get());
				}
				else
					throw new ContentNotFoundException("Cannot add Updated User , Not present with Id: "+updatedId);

				bankRepo.save(isBankDetailPresent.get());
				return isBankDetailPresent.get();
			}
			else
				throw new ContentNotFoundException("Bank Details not present ai id : "+bankDetailsId);
		}
		else
			throw new ContentNotFoundException("Authentaction Failed");
	}

	@Override
	public BankDetailsModel delete(String token, long bankDetailsId) {
		long id = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(id);
		if(isAdminPresent.isPresent()) {
			Optional<BankDetailsModel> bankDetail = bankRepo.findById(bankDetailsId);
			if(bankDetail.isPresent()) {
				bankRepo.delete(bankDetail.get());
				return bankDetail.get();
			}
			else
				throw new ContentNotFoundException("No Details with id: "+ bankDetailsId);
		}
		else
			throw new ContentNotFoundException("Authentaction Failed");
	}
	
	
}
