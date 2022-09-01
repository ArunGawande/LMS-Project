package com.bridgelabz.lmsprojects.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.lmsprojects.Dto.HiringCandidateDto;
import com.bridgelabz.lmsprojects.Model.HiringCandidateModel;
import com.bridgelabz.lmsprojects.Service.IHiringCandidateService;

@RestController
@RequestMapping("/hiringcandidate")
public class HiringCandidateController {

	@Autowired
	IHiringCandidateService hiringCandidateService;
	
	@PostMapping("/create")
	public HiringCandidateModel create(@RequestHeader String token,@RequestBody HiringCandidateDto hiringCandidateDto) {
		return hiringCandidateService.create(token,hiringCandidateDto);
	}
	
	@GetMapping("/read")
	public List<HiringCandidateModel> read(@RequestHeader String token){
		return hiringCandidateService.read(token);
	}
	
	@PutMapping("/update")
	public HiringCandidateModel update(@RequestHeader String token ,@RequestBody HiringCandidateDto hiringCandidateDto,@RequestParam long id,
										@RequestParam long bankDetailId) {
		return hiringCandidateService.update(token,hiringCandidateDto,id,bankDetailId);
	}
	
	@DeleteMapping("/delete")
	public HiringCandidateModel delete(@RequestHeader String token ,@RequestParam long id) {
		return hiringCandidateService.delete(token,id);
	}
	
}
