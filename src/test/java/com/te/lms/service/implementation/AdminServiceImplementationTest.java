
package com.te.lms.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.google.common.collect.Lists;
import com.te.lms.dto.MentorDto;
import com.te.lms.dto.TechnologiesDto;
import com.te.lms.entity.Mentor;
import com.te.lms.enums.Status;
import com.te.lms.repository.MentorRepository;
import com.te.lms.service.AdminService;


@SpringBootTest
class AdminServiceImplementationTest {

	@Autowired
	private AdminService adminService;

	@MockBean
	private MentorRepository mentorRepository;

	/*
	 * @BeforeEach public void setup() {
	 * 
	 * }
	 */

	
	@Test
	public void testregisterMentor() {
		List<TechnologiesDto> technologiesDtos = Lists.newArrayList();
		technologiesDtos.add(TechnologiesDto.builder().technology("java").build());
		technologiesDtos.add(TechnologiesDto.builder().technology("sql").build());
		MentorDto mentorDto = MentorDto.builder().employeeId("T-123").mentorName("Avi").mentorEmailId("123@xyz")
							.status(Status.ACTIVE).skills(technologiesDtos).build();
		Mentor mentor = new Mentor();
		BeanUtils.copyProperties(mentorDto, mentor);
		Mockito.when(mentorRepository.save(mentor)).thenReturn(mentor);
		AdminServiceImplementationTest a = new AdminServiceImplementationTest();
		assertEquals(Optional.of(true), adminService.registerMentor(mentorDto));
	}

}
