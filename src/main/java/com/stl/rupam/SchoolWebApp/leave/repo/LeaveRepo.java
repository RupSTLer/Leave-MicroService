package com.stl.rupam.SchoolWebApp.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stl.rupam.SchoolWebApp.leave.entity.Leave;

public interface LeaveRepo extends JpaRepository<Leave, Long>{
	
	List<Leave> findByStudentId(Long studentId);

}
