package com.bridgelabz.lmsprojects.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bridgelabz.lmsprojects.Dto.TechStackDto;
import com.bridgelabz.lmsprojects.Model.TechStackModel;
import com.bridgelabz.lmsprojects.Service.ITechStackService;

@RestController
@RequestMapping("/techstack")
public class TechStackController {

	@Autowired ITechStackService techService;
	
	@PostMapping("/create")
	public TechStackModel create(@RequestHeader String token,@RequestBody TechStackDto techStackDto,@RequestParam long creatorUserId) {
		return techService.create(token,techStackDto,creatorUserId);
	}
	
	@GetMapping("/read")
	public List<TechStackModel> read(@RequestHeader String token){
		return techService.read(token);
	}
	
	@PutMapping("/update")
	public TechStackModel update(@RequestHeader String token,@RequestBody TechStackDto techStackDto,@RequestParam long techStackId,@RequestParam long creatorUserId) {
		return techService.update(token,techStackDto,techStackId,creatorUserId);
	}

	@DeleteMapping("/delete")
	public TechStackModel delete(@RequestHeader String token,@RequestParam long id) {
		return techService.delete(token,id);
	}
}