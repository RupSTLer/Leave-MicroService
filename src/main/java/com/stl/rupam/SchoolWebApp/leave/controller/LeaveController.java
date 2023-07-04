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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/leave")
@Api(tags = "Leave Service APIs", value = "Leave Controller", description = "it will handle the web requests of leave service")
public class LeaveController {

	@Autowired
	private LeaveService leaveService;
	
	@ApiOperation(value = "Apply new Leave", notes = "returns a string when successfully applied")
	@PostMapping("/applyLeave")
	public String applyLeave(@Valid @RequestBody Leave leave){
		return leaveService.applyLeave(leave);
	}
	
	@ApiOperation(value = "update applied Leave details", notes = "returns a string when successfully updated")
	@PutMapping("/updateLeave")
	public String updateLeave(@Valid @RequestBody Leave leave){
		return leaveService.updateLeave(leave);
	}
	
	@ApiOperation(value = "List all the applied Leaves", notes = "returns a list of leaves")
	@GetMapping("/listLeaves")
	public List<Leave> listLeaves()
	{
		return leaveService.listLeaves();
	}
	
	@ApiOperation(value = "Get Leave details by studentID", notes = "returns a Leave object")
	@GetMapping("/getLeaveDetailsByStudentId/{studentId}")
	public List<Leave> getLeaveDetailsByStudentId(@PathVariable String studentId)
	{
		return leaveService.getLeaveDetailsByStudentId(studentId);
	}
	
	@ApiOperation(value = "Approve applied leaves by Teacher & Admin")
	@PutMapping("/approve/{id}")
	public void approveLeave(@PathVariable Long id)
	{
		leaveService.approveLeave(id);
	}
	
	@ApiOperation(value = "Reject applied leaves by Teacher & Admin")
	@PutMapping("/reject/{id}")
	public void rejectLeave(@PathVariable Long id)
	{
		leaveService.rejectLeave(id);
	}
}
