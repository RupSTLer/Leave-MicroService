package com.stl.rupam.SchoolWebApp.leave.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stl.rupam.SchoolWebApp.leave.entity.Leave;
import com.stl.rupam.SchoolWebApp.leave.entity.Student;
import com.stl.rupam.SchoolWebApp.leave.repo.LeaveRepo;

@Service
public class LeaveService {

	@Autowired
	private LeaveRepo leaveRepo;

	public String applyLeave(Leave leave) {
		
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String timestamp = datetime.format(format);
		leave.setTime(timestamp);
		
		String s = validateLeave(leave);
		
		if(s == null)
		{
			leaveRepo.save(leave);
			return "Leave applied";
		}
		else
		{
			return s;
		}
		
	}
	
	private String validateLeave(Leave leave)
	{
		try {

			if(leave.getStartDate().isAfter(leave.getEndDate())) {
				
				throw new IllegalArgumentException("EndDate must be greater than startDate");
			}
			
			List<Leave> existingLeave = leaveRepo.findByStudentIdAndStartDateAndEndDate(
					leave.getStudentId(), leave.getStartDate(), leave.getEndDate());
			
			if(!existingLeave.isEmpty())
			{
				throw new IllegalArgumentException("Leave already applied for the same dates");

			}
			
			if(leave.getReason().length() > 100)
			{
				throw new IllegalArgumentException("Maximum character exceeded");
			}
			
			Student student = StudentRest.getStudentByStudentId(leave.getStudentId());
			
			if(student == null)
			{
				throw new IllegalArgumentException("Invalid student");
			}
			
		}
		catch(Exception ex)
		{
			return ex.getMessage();
		}
		
		
		return null;
	}


	public String updateLeave(Leave leave) {
		
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String timestamp = datetime.format(format);
		leave.setTime(timestamp);
		
		String s = validateLeave(leave);
		
		if(s == null)
		{
			leaveRepo.saveAndFlush(leave);
			return "Leave updated";
		}
		else
		{
			return s;
		}
		
	}

	public List<Leave> getLeaveDetailsByStudentId(String studentId) {
		List<Leave> leaves = leaveRepo.getLeavesListByStudentId(studentId);
		sortLeavesByTime(leaves);
		return leaves;
//				.orElseThrow(() -> new com.stl.rupam.SchoolWebApp.leave.exception.ResourceNotFoundException("Leave not exist with studentId: " + studentId));
	}

	public List<Leave> listLeaves() {
		List<Leave> leaves = leaveRepo.findAll();
		sortLeavesByTime(leaves);
		return leaves;
	}
	
	public void sortLeavesByTime(List<Leave> leaves)
	{
		Collections.sort(leaves, new Comparator<Leave>() {

			@Override
			public int compare(Leave leave1, Leave leave2) {
				String time1 = leave1.getTime();
				String time2 = leave2.getTime();				
				return time2.compareTo(time1);
			}
		});
	}

	public void approveLeave(Long id) {
		leaveRepo.approveLeave(id);
	}

	public void rejectLeave(Long id) {
		leaveRepo.rejectLeave(id);
	}

}


