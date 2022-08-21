package com.bridgelabz.lmsprojects.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.lmsprojects.Dto.CandidateDto;
import com.bridgelabz.lmsprojects.Model.CandidateModel;
import com.bridgelabz.lmsprojects.Service.ICandidateService;
import com.bridgelabz.lmsprojects.Util.StatusCount;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
	
	@Autowired
	ICandidateService candidateService;
	
	@PostMapping("/createcandidate")
	public CandidateModel create( @RequestHeader String token,@RequestBody CandidateDto candidateDto) {
		return candidateService.create(token,candidateDto);
	}
	
	@GetMapping("/readcandidate")
	public List<CandidateModel> read(@RequestHeader String token){
		return candidateService.read(token);
	}
	
	@PutMapping("/updatecandidate/{candidateId}")
	public CandidateModel update(@RequestHeader String token,@PathVariable long candidateId,@RequestBody CandidateDto candidateDto) {
		return candidateService.update(token,candidateId,candidateDto);
	}
	
	@DeleteMapping("/deletecandidate/{candidateId}")
	public CandidateModel delete(@RequestHeader String token,@PathVariable long candidateId) {
		return candidateService.delete(token, candidateId);
	}

	@GetMapping("/getbyststus/{status}")
	public List<CandidateModel> getByStatus(@RequestHeader String token,@PathVariable String status) {
		return candidateService.getByStatus(token,status);
	}
	
	@PutMapping("/changeStatus/{candidateId}")
	public CandidateModel chagneStatus(@RequestHeader String token,@PathVariable long candidateId,@RequestParam String newStatus) {
		return candidateService.changeStatus(token,candidateId,newStatus);
	}
	
	@GetMapping("getcountbystatus")
	public StatusCount getCountByStatus(@RequestHeader String token) {
		 return candidateService.getCountByStatus(token);
	}
	
}
