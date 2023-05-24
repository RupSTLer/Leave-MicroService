package com.stl.rupam.SchoolWebApp.leave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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

@SpringBootTest(classes = { LeaveCRUDTest.class })
public class LeaveCRUDTest {

	@Mock
	LeaveRepo leaveRepo;

	@InjectMocks
	LeaveService leaveService;

	// JUnit test for apply Leave
	@Test
	@Order(1)
	@Rollback(value = false)
	public void applyLeaveTest() {
		
		Leave leave = new Leave(2L, "SMS005", "2023-10-12", "2023-10-15", true, "event");
		
		when(leaveRepo.save(leave)).thenReturn(leave);
		assertEquals(leave, leaveService.applyLeave(leave));

	}

//	@Test
//	@Order(2)
////	@Rollback(value = false)
//	public void getLeaveTest() {
//		
//		List<Leave> leaves = new ArrayList<Leave>();
//		
//		leaves.add(new Leave(2L, "SMS005", "2023-10-12", "2023-10-15", true, "event"));
//		leaves.add(new Leave(3L, "SMS005", "2023-10-12", "2023-10-15", true, "event"));
//		
//		
//		
//		assertEquals(leaveRepo.findById(2L).get(), leaveRepo.findByStudentId(2L));
//		
//	}

	@Test
	@Order(2)
	@Rollback(value = false)
	public void getListOfLeavesTest() {
		
		List<Leave> leaves = new ArrayList<Leave>();
		
		leaves.add(new Leave(2L, "SMS005", "2023-10-12", "2023-10-15", true, "event"));
		leaves.add(new Leave(3L, "SMS005", "2023-10-12", "2023-10-15", true, "event"));

		when(leaveRepo.findAll()).thenReturn(leaves);
		assertEquals(2, leaveService.listLeaves().size());

	}

	@Test
	@Order(3)
	@Rollback(value = false)
	public void updateLeaveTest() {
		Leave leave = Leave.builder().id(2L).studentId("SMS002").startDate("2023-10-12").endDate("2023-10-15")
				.status(true).reason("event").build();

		leave.setStudentId("SMS005");
 
		leaveService.updateLeave(leave);

		Assertions.assertThat(leave.getStudentId()).isEqualTo("SMS005");
	}

}
