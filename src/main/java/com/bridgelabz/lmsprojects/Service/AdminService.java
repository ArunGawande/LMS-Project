package com.bridgelabz.lmsprojects.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.lmsprojects.Dto.AdminDto;
import com.bridgelabz.lmsprojects.Exception.ContentNotFoundException;
import com.bridgelabz.lmsprojects.Model.AdminModel;
import com.bridgelabz.lmsprojects.Repository.IAdminRepository;
import com.bridgelabz.lmsprojects.Util.Response;
import com.bridgelabz.lmsprojects.Util.TokenUtil;

@Service
public class AdminService implements IAdminService{
	
	@Autowired
	IAdminRepository adminRepo;
	
	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	MailService mailService;	

	@Override
	public AdminModel createAdmin(AdminDto adminDto) {
		AdminModel adminModel = new AdminModel(adminDto);
		adminRepo.save(adminModel);
		return adminModel;
	}

	@Override
	public List<AdminModel> readAdmin() {
		List<AdminModel> adminList = adminRepo.findAll();
		if(!adminList.isEmpty())
			return adminList;
		throw new ContentNotFoundException("No Data");
	}

	@Override
	public AdminModel update(AdminDto adminDto,long id) {
		Optional<AdminModel> isAdminPresent = adminRepo.findById(id);
		if(isAdminPresent.isPresent()) {
			isAdminPresent.get().setFirstName(adminDto.getFirstName());
			isAdminPresent.get().setLastName(adminDto.getLastName());
			isAdminPresent.get().setMobile(adminDto.getMobile());
			isAdminPresent.get().setEmailId(adminDto.getEmailId());
			isAdminPresent.get().setPassword(adminDto.getPassword());
			isAdminPresent.get().setProfilePath(adminDto.getProfilePath());
			isAdminPresent.get().setStatus(adminDto.getStatus());
			adminRepo.save(isAdminPresent.get());
		}
		throw new ContentNotFoundException("No Admin with id : "+id);
	}

	@Override
	public AdminModel delete(long id) {
		Optional<AdminModel> isAdminPresent = adminRepo.findById(id);
		if(isAdminPresent.isPresent()) {
			adminRepo.delete(isAdminPresent.get());
		}
		throw new ContentNotFoundException("No Admin Present with id : "+id);
	}

	@Override
	public Response login(String mail, String password) {

		Optional<AdminModel> isAdminPresent = adminRepo.findByEmailId(mail);
		if(isAdminPresent.isPresent()) {
			if(isAdminPresent.get().getPassword().equals(password)) {
				String token = tokenUtil.createToken(isAdminPresent.get().getId());	
				return new Response(token);
			}else
				throw new ContentNotFoundException("incorrect password");
		}else
			throw new ContentNotFoundException("incorrect password");
	}

	@Override
	public Response sendResetLink(String mail) {
		Optional<AdminModel> isAdminPresent = adminRepo.findByEmailId(mail);
		if(isAdminPresent.isPresent()) {
			String token = tokenUtil.createToken(isAdminPresent.get().getId());
			String url ="http://localhost/changepassword/newpasswordhere";
			String subject="Reset password";
			String body="Link: "+url+"/"+token+"?newPassword=desiredpasswordhere"+"\n token in pathvariable and newpassword is requestparam";	
			mailService.send(isAdminPresent.get().getEmailId(), subject, body);
			return new Response(token);
		}
		else
			throw new ContentNotFoundException("mail id not present");
	}

	@Override
	public AdminModel changePassword(String token,String newPassword) {
		long id = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(id);
		if(isAdminPresent.isPresent()) {
			isAdminPresent.get().setPassword(newPassword);
			adminRepo.save(isAdminPresent.get());
		}
		else
			throw new ContentNotFoundException("mail id not present");
		return isAdminPresent.get();
	}

	@Override
	public AdminModel changeProfile(String newPath,String token) {
		long id = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepo.findById(id);
		if(isAdminPresent.isPresent()) {
			isAdminPresent.get().setProfilePath(newPath);
			adminRepo.save(isAdminPresent.get());
			return isAdminPresent.get();
		}
		throw new ContentNotFoundException("Admin id not present, check token : "+token);	
	}
	
	
	
	
}
