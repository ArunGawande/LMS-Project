package com.bridgelabz.lmsprojects.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.lmsprojects.Model.MentorsModel;

public interface IMentorsRepository extends JpaRepository<MentorsModel ,Long>{

	@Query("SELECT COUNT(y) FROM mentors_model y WHERE mentorRole =?1")
	public long getCountByRole(String role);

}
