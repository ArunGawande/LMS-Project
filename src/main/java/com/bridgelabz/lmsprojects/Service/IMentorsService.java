package com.bridgelabz.lmsprojects.Service;

import java.util.List;
import java.util.Map;

import com.bridgelabz.lmsprojects.Dto.MentorsDto;
import com.bridgelabz.lmsprojects.Model.MentorsModel;

public interface IMentorsService {

	MentorsModel create(String token, MentorsDto mentorsDto);

	List<MentorsModel> read(String token);

	MentorsModel update(String token, MentorsDto mentorsDto, long id);

	MentorsModel delete(String token, long mentorsId);

	Map<String, Long> getRolesCounts(String token);

	MentorsModel fetchDetail(String token, long mentorId);

}
