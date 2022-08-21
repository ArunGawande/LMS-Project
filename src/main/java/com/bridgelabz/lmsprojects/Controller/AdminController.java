package com.bridgelabz.lmsprojects.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bridgelabz.lmsprojects.Dto.AdminDto;
import com.bridgelabz.lmsprojects.Model.AdminModel;
import com.bridgelabz.lmsprojects.Service.IAdminService;
import com.bridgelabz.lmsprojects.Util.Response;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	IAdminService adminService;

	@GetMapping("/readAdmin")
	public List<AdminModel> readAdmin(){
		return adminService.readAdmin();
	}

	@PostMapping("/create")
	public AdminModel createAdmin(@Valid @RequestBody AdminDto adminDto) {

		return adminService.createAdmin(adminDto);
	}
	

	@PutMapping("/updateAdmin/{id}")
	public AdminModel update(@RequestBody AdminDto adminDto,@PathVariable long id) {
		return adminService.update(adminDto,id);
	}
	
	@DeleteMapping("/deleteAdmin/{id}")
	public AdminModel delete(@PathVariable long id) {

		return adminService.delete(id);
	}
	
	@GetMapping("/loginAdmin/{mail}/{password}")
	public Response login(@PathVariable String mail,@PathVariable String password) {
		return adminService.login(mail, password);
	}
	
	@PostMapping("/sendresetlink/{mail}")
	public Response sendResetLink(@PathVariable String mail) {

		return adminService.sendResetLink(mail);
	}
	
	@PostMapping("/resetpassword/{token}")
	public AdminModel changePassword(@PathVariable String token,@RequestParam String newPassword) {
		return adminService.changePassword(token,newPassword);
	}
	
	@PostMapping("/changeprofile")
	public AdminModel changeProfile(@RequestParam String newPath,@RequestHeader String token) {
		
		return adminService.changeProfile(newPath,token);
	}
}
