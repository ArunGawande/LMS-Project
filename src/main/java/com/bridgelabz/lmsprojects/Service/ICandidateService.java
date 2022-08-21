package com.bridgelabz.lmsprojects.Service;

import java.util.List;

import com.bridgelabz.lmsprojects.Dto.CandidateDto;
import com.bridgelabz.lmsprojects.Model.CandidateModel;
import com.bridgelabz.lmsprojects.Util.StatusCount;

public interface ICandidateService {

	CandidateModel create(String token, CandidateDto candidateDto);

	List<CandidateModel> read(String token);

	CandidateModel update(String token, long candidateId, CandidateDto candidateDto);

	CandidateModel delete(String token, long candidateId);

	List<CandidateModel> getByStatus(String token, String status);

	CandidateModel changeStatus(String token, long candidateId, String newStatus);

	StatusCount getCountByStatus(String token);

}
