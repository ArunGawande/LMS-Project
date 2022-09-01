package com.bridgelabz.lmsprojects.Service;

import java.util.List;

import com.bridgelabz.lmsprojects.Dto.TechStackDto;
import com.bridgelabz.lmsprojects.Model.TechStackModel;

public interface ITechStackService {

	TechStackModel create(String token, TechStackDto techStackDto, long creatorUserId);

	List<TechStackModel> read(String token);

	TechStackModel update(String token, TechStackDto techStackDto, long techStackId, long creatorUserId);

	TechStackModel delete(String token, long id);

}
