package com.stl.rupam.SchoolWebApp.leave.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stl.rupam.SchoolWebApp.leave.entity.Leave;
import com.stl.rupam.SchoolWebApp.leave.service.LeaveService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/leave")
public class LeaveController {

	@Autowired
	private LeaveService leaveService;
	
	@PostMapping("/applyLeave")
	public String applyLeave(@Valid @RequestBody Leave leave){
		return leaveService.applyLeave(leave);
	}
	
	@PutMapping("/updateLeave")
	public String updateLeave(@Valid @RequestBody Leave leave){
		return leaveService.updateLeave(leave);
	}
	
	@GetMapping("/listLeaves")
	public List<Leave> listLeaves()
	{
		return leaveService.listLeaves();
	}
	
	@GetMapping("/getLeaveDetailsByStudentId/{studentId}")
	public List<Leave> getLeaveDetailsByStudentId(@PathVariable String studentId)
	{
		return leaveService.getLeaveDetailsByStudentId(studentId);
	}
	
	@PutMapping("/approve/{id}")
	public void approveLeave(@PathVariable Long id)
	{
		leaveService.approveLeave(id);
	}
	
	@PutMapping("/reject/{id}")
	public void rejectLeave(@PathVariable Long id)
	{
		leaveService.rejectLeave(id);
	}
}
