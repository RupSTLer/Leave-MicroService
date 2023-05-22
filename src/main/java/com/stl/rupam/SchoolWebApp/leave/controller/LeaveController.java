package com.stl.rupam.SchoolWebApp.leave.controller;

import java.util.List;

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
	
	@GetMapping("/getLeaveDetails/{id}")
	public List<Leave> getLeaveDetails(@PathVariable Long id){
		return leaveService.getLeaveDetails(id);
	}
	
	@PostMapping("/applyLeave")
	public Leave applyLeave(@RequestBody Leave leave){
		return leaveService.applyLeave(leave);
	}
	@PutMapping("/updateLeave")
	public Leave updateLeave(@RequestBody Leave leave){
		return leaveService.updateLeave(leave);
	}
	
	@GetMapping("/listLeaves")
	public List<Leave> listLeaves()
	{
		return leaveService.listLeaves();
	}
}
