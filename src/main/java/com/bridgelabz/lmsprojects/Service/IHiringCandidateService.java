package com.bridgelabz.lmsprojects.Service;

import java.util.List;

import com.bridgelabz.lmsprojects.Dto.HiringCandidateDto;
import com.bridgelabz.lmsprojects.Model.HiringCandidateModel;

public interface IHiringCandidateService {

	HiringCandidateModel create(String token, HiringCandidateDto hiringCandidateDto);

	List<HiringCandidateModel> read(String token);

	HiringCandidateModel update(String token, HiringCandidateDto hiringCandidateDto, long id, long bankDetailId);

	HiringCandidateModel delete(String token, long id);

}
