package com.bridgelabz.lmsprojects.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bridgelabz.lmsprojects.Model.CandidateModel;

public interface ICandidateRepository extends JpaRepository<CandidateModel, Long>{

	@Query("SELECT x FROM CandidateModel x WHERE x.status= ?1")
	public List<CandidateModel> findAllByStatus(String status);


	@Query("SELECT COUNT(y) FROM CandidateModel y WHERE status =?1")
	public long getCountByStatus(String status);

}
