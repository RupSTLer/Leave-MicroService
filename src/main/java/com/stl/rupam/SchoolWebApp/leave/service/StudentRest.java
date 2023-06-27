package com.stl.rupam.SchoolWebApp.leave.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.stl.rupam.SchoolWebApp.leave.entity.Student;

@Component
public class StudentRest {
	
//	@Autowired
//	private static RestTemplate restTemplate;
	
	static RestTemplate restTemplate = new RestTemplate();
	
//	@Autowired
//	public StudentRest(RestTemplate restTemplate)
//	{
//		this.restTemplate = restTemplate;
//	}
	
	public static Student getStudentByStudentId(String studentId)
	{
		String url = "http://127.0.0.1:9003/student/" + studentId;
		ResponseEntity<Student> response = restTemplate.getForEntity(url, Student.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
		else 
		{
			throw new IllegalArgumentException("Invalid Student Id");
		}
	}

}
