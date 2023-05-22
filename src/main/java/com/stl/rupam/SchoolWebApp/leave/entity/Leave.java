package com.stl.rupam.SchoolWebApp.leave.entity;

import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
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
	
	@Column(nullable = false)
	private String startDate;	
	
	@Column(nullable = false)
	private String endDate; 
	
//	@Column(nullable = false)
	private Boolean status;
	
	@Max(value = 100, message = "character limit is 100")
	private String reason;

      
	
}