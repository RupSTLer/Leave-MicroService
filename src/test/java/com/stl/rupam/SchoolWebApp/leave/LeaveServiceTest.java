package com.stl.rupam.SchoolWebApp.leave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.stl.rupam.SchoolWebApp.leave.entity.Leave;
import com.stl.rupam.SchoolWebApp.leave.repo.LeaveRepo;
import com.stl.rupam.SchoolWebApp.leave.service.LeaveService;

@SpringBootTest(classes = { LeaveServiceTest.class })
public class LeaveServiceTest {

	@Mock
	LeaveRepo leaveRepo;

	@InjectMocks
	LeaveService leaveService;

	// JUnit test for apply Leave
	@Test
	@Order(1)
	@Rollback(value = false)
	public void applyLeaveTest() {
				
		Leave mockLeave = new Leave(2L, "SMS002", LocalDate.of(2023, 5, 30), LocalDate.of(2023, 5, 31), "pending", "event", "20-11-2023 02:14");
		
		when(leaveRepo.save(mockLeave)).thenReturn(mockLeave);   //mocking
		
		assertEquals("Leave applied", leaveService.applyLeave(mockLeave));
		verify(leaveRepo, times(1)).save(mockLeave);

	}

	@Test
	@Order(2)
	@Rollback(value = false)
	public void getLeavesListByStudentIdTest() {
		
		String studentId = "SMS002";
		List<Leave> mockLeaveList = new ArrayList<Leave>();
		
		mockLeaveList.add(new Leave(2L, studentId, LocalDate.of(2023, 5, 30), LocalDate.of(2023, 5, 31), "pending", "event", "20-11-2023 02:14"));
		mockLeaveList.add(new Leave(3L, studentId, LocalDate.of(2023, 5, 30), LocalDate.of(2023, 5, 31), "pending", "sick", "20-11-2023 02:14"));
			
		when(leaveRepo.getLeavesListByStudentId(studentId)).thenReturn(mockLeaveList);
		
		List<Leave> mockService = leaveService.getLeaveDetailsByStudentId(studentId);
		
		assertEquals(mockLeaveList, mockService);
		verify(leaveRepo, times(1)).getLeavesListByStudentId(studentId);
				
	}

	// JUnit test for getListOfLeavesTest
	@Test
	@Order(3)
	@Rollback(value = false)
	public void getListOfLeavesTest() {
		
		List<Leave> mockLeaveList = new ArrayList<Leave>();
		
		mockLeaveList.add(new Leave(2L, "SMS001", LocalDate.of(2023, 5, 30), LocalDate.of(2023, 5, 31), "pending", "event", "20-11-2023 02:14"));
		mockLeaveList.add(new Leave(3L, "SMS002", LocalDate.of(2023, 5, 30), LocalDate.of(2023, 5, 31), "pending", "sick", "20-11-2023 02:14"));
		
		when(leaveRepo.findAll()).thenReturn(mockLeaveList);
		
		List<Leave> mockService = leaveService.listLeaves();
		assertEquals(mockLeaveList, mockService);
		verify(leaveRepo, times(1)).findAll();
		
	}

	// JUnit test for update Leaves details
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateLeaveTest() {

		Leave leave = new Leave(2L, "SMS005", LocalDate.of(2023, 5, 30), LocalDate.of(2023, 5, 31), "pending", "event", "20-11-2023 02:14");

		leave.setStudentId("SMS005");
 
		leaveService.updateLeave(leave);

		Assertions.assertThat(leave.getStudentId()).isEqualTo("SMS005");
	}

}
