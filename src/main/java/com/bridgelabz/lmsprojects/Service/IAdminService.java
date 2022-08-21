package com.bridgelabz.lmsprojects.Service;

import java.util.List;

import com.bridgelabz.lmsprojects.Dto.AdminDto;
import com.bridgelabz.lmsprojects.Model.AdminModel;
import com.bridgelabz.lmsprojects.Util.Response;

public interface IAdminService {

	List<AdminModel> readAdmin();
	AdminModel createAdmin(AdminDto adminDto);

	AdminModel update(AdminDto adminDto, long id);

	AdminModel delete(long id);

	Response login(String mail, String password);

	Response sendResetLink(String mail);

	AdminModel changePassword(String token, String newPassword);

	AdminModel changeProfile(String token, String token2);

}
