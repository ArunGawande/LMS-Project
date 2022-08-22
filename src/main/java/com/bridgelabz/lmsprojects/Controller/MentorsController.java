package com.bridgelabz.lmsprojects.Controller;

import com.bridgelabz.lmsprojects.Dto.MentorsDto;
import com.bridgelabz.lmsprojects.Model.MentorsModel;
import com.bridgelabz.lmsprojects.Service.IMentorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mentors")
public class MentorsController {
	
	@Autowired
	IMentorsService mentorsService;
	
	@PostMapping("/createMentors")
	public MentorsModel create(@RequestHeader String token, @RequestBody MentorsDto mentorsDto) {
		return mentorsService.create(token,mentorsDto);
	}
	
	@GetMapping("/readMentors")
	public List<MentorsModel> read(@RequestHeader String token){
		return mentorsService.read(token);
	}
	
	@PutMapping("/updateMentors/{id}")
	public MentorsModel update(@RequestHeader String token,@RequestBody MentorsDto mentorsDto,@PathVariable long id) {
		return mentorsService.update(token,mentorsDto,id);
	}
	
	@DeleteMapping("/deleteMentors")
	public MentorsModel delete(@RequestHeader String token,@PathVariable long mentorsId) {
		return mentorsService.delete(token,mentorsId);
	}
	
	@GetMapping("/getrolescounts/{role}")
	public Map<String,Long> getRolesCounts(@RequestHeader String token){
		return mentorsService.getRolesCounts(token);
	}
	
	@GetMapping("/fetchdetails/{mentorId")
	public MentorsModel fetchDetail(@RequestHeader String token,@PathVariable long mentorId) {
		return mentorsService.fetchDetail(token,mentorId);
	}
}
