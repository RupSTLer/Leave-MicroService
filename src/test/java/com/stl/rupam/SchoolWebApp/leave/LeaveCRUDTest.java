package com.stl.rupam.SchoolWebApp.leave;

import java.util.List;
import javax.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.stl.rupam.SchoolWebApp.leave.entity.Leave;
import com.stl.rupam.SchoolWebApp.leave.repo.LeaveRepo;



//@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@EnableAutoConfiguration(exclude=AutoConfigureTestDatabase.class)
@AutoConfigureTestDatabase(replace= Replace.NONE)
public class LeaveCRUDTest {
	
	@Autowired
	private LeaveRepo leaveRepo;
	
	//JUnit test for save teacher
	@Test
	@Order(1)
	@Rollback(value = false)
	public void applyLeaveTest()
	{
		Leave leave = Leave.builder()
				.id(2L)
				.studentId("SMS002")
				.startDate("2023-10-12")
				.endDate("2023-10-15")
				.status(true)
				.reason("event")
				.build();
				
		
		leaveRepo.save(leave);
		
		Assertions.assertThat(leave.getId()).isGreaterThan(0);
	}
	
	@Test
	@Order(2)
//	@Rollback(value = false)
	public void getLeaveTest()
	{
		Leave leave = leaveRepo.findById(2L).get();
		
		Assertions.assertThat(leave.getId()).isEqualTo(2L);
	}
	
	@Test
	@Order(3)
//	@Rollback(value = false)
	public void getListOfLeavesTest()
	{
		
		List<Leave> leaves = leaveRepo.findAll();
		
		Assertions.assertThat(leaves.size()).isEqualTo(2);
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateLeaveTest()
	{
		Leave leave = leaveRepo.findById(2L).get();
		
		leave.setStudentId("SMS005");
		
		Leave leaveUpdated = leaveRepo.save(leave);
		
		Assertions.assertThat(leaveUpdated.getStudentId()).isEqualTo("SMS005");
	}
	

}
