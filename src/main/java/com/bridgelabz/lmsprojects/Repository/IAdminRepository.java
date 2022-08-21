package com.bridgelabz.lmsprojects.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.lmsprojects.Model.AdminModel;


public interface IAdminRepository extends JpaRepository<AdminModel, Long >{

	Optional<AdminModel> findByEmailId(String mail);

}
