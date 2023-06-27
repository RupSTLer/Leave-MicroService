package com.stl.rupam.SchoolWebApp.leave.repo;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stl.rupam.SchoolWebApp.leave.entity.Leave;

public interface LeaveRepo extends JpaRepository<Leave, Long> {

	List<Leave> findByStudentIdAndStartDateAndEndDate(String studentId, LocalDate startDate, LocalDate endDate);
	
	List<Leave> getLeavesListByStudentId(String studentId);

	@Transactional
	@Modifying
	@Query(value = "update leaves l set l.status = ('approved') where l.id = ?", nativeQuery = true)
	void approveLeave(@Param("id") Long id);

	@Transactional
	@Modifying
	@Query(value = "update leaves l set l.status = ('rejected') where l.id = ?", nativeQuery = true)
	void rejectLeave(@Param("id") Long id);

}
