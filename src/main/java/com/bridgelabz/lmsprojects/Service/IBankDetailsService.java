package com.bridgelabz.lmsprojects.Service;

import java.util.List;

import com.bridgelabz.lmsprojects.Dto.BankDetailsDto;
import com.bridgelabz.lmsprojects.Model.BankDetailsModel;

public interface IBankDetailsService {

	BankDetailsModel create(String token, BankDetailsDto bankDetailsDto, long creatorId, long updatedId);

	List<BankDetailsModel> read(String token);

	BankDetailsModel update(String token, long creatorId, long updatedId, long bankDetailsId, BankDetailsDto bankDetailsDto);

	BankDetailsModel delete(String token, long bankDetailsId);

}
