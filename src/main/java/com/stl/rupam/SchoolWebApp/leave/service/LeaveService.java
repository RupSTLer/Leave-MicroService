package com.stl.rupam.SchoolWebApp.leave.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stl.rupam.SchoolWebApp.leave.entity.Leave;
import com.stl.rupam.SchoolWebApp.leave.repo.LeaveRepo;

@Service
public class LeaveService {

	@Autowired
	private LeaveRepo repo;

	public Leave applyLeave(Leave leave) {
		
		String startDateIn = leave.getStartDate();
		String endDateIn = leave.getEndDate();
	
		//validating date inputs
		int compare = endDateIn.compareTo(startDateIn);
		
		System.out.println(compare);
		
		if(compare > 0)
		{
			return repo.save(leave);
		}
		
		return null;		
	}

	public Leave updateLeave(Leave leave) {
		return repo.saveAndFlush(leave);
	}

	public List<Leave> getLeaveDetails(Long id) {
		return repo.findByStudentId(id);
	}

	public List<Leave> listLeaves() {
		return repo.findAll();
	}

}
