package com.bridgelabz.lmsprojects.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bridgelabz.lmsprojects.Dto.BankDetailsDto;
import com.bridgelabz.lmsprojects.Model.BankDetailsModel;
import com.bridgelabz.lmsprojects.Service.IBankDetailsService;

@RestController
@RequestMapping("/bank")
public class BankDetailsController {

	@Autowired
	IBankDetailsService bankService;
	
	@PostMapping("/create")
	public BankDetailsModel create(@RequestHeader String token , @RequestBody BankDetailsDto bankDetailsDto,
								@RequestParam long creatorId,@RequestParam long updatedId) {
		return bankService.create(token,bankDetailsDto,creatorId,updatedId);
	}
	
	@GetMapping("/read")
	public List<BankDetailsModel> read(@RequestHeader String token){
		return bankService.read(token);
	}
	
	@PutMapping("/update")
	public BankDetailsModel update(@RequestHeader String token,@RequestParam long creatorId,@RequestParam long updatedId,
									@RequestParam long bankDetailsId,@RequestBody BankDetailsDto bankDetailsDto){
		return bankService.update(token,creatorId,updatedId,bankDetailsId,bankDetailsDto);
	}
	
	@DeleteMapping("/delete")
	public BankDetailsModel delete(@RequestHeader String token,long bankDetailsId) {
		return bankService.delete(token,bankDetailsId);
	}
	
}
