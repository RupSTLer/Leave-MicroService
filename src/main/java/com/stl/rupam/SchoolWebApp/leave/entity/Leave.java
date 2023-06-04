package com.stl.rupam.SchoolWebApp.leave.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "leaves")
public class Leave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Pattern(regexp = "^[SMS]{3}[0-9]{3}$", message = "please add valid ID")
	private String studentId;
	
//	@NotEmpty(message = "student name is mandetory")
	@Pattern(regexp = "[a-zA-Z]{2}[a-zA-Z ]+", message = "please add valid name")
	private String studentName;
	
	@Column(nullable = false)
	private LocalDate startDate;	
	
	@Column(nullable = false)
	private LocalDate endDate; 
	
//	@Column(nullable = false)
	private String status = "pending";
	
	@Size(max = 100, message = "character limit is 100")
	private String reason;
	
	private String time;

    
}