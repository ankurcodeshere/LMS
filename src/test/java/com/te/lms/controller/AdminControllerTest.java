package com.te.lms.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.te.lms.dto.ApproveDto;
import com.te.lms.dto.BatchRegisterDto;
import com.te.lms.dto.BatchUpdateDto;
import com.te.lms.dto.MentorDto;
import com.te.lms.dto.MentorUpdateDto;
import com.te.lms.dto.RejectDto;
import com.te.lms.dto.RequestListMessageDto;
import com.te.lms.entity.Batch;
import com.te.lms.enums.BatchStatus;
import com.te.lms.enums.Status;
import com.te.lms.service.AdminService;

@AutoConfigureMockMvc
@SpringBootTest
class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	/*
	 * @Autowired 
	 * private WebApplicationContext context;
	 */

	@MockBean
	private AdminService adminService;

	@InjectMocks
	private AdminController adminController;

	ObjectMapper objectMapper = new ObjectMapper();

	
	  @Before public void setUp() {
	  
	  mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
	  	// below code is giving Null Pointer exception.
		/* mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); */
	   }
	 

	@Test
	public void testMentorRegister() throws Exception {
	   MentorDto mentordto =
				 MentorDto.builder().employeeId("123").mentorEmailId("xyz@gmail.com")
				.mentorName("Ajay").status(Status.ACTIVE).skills(Lists.newArrayList()).build();
		 Mockito.when(adminService.registerMentor(Mockito.any())).thenReturn(Optional.ofNullable("123"));
		 mockMvc.perform(MockMvcRequestBuilders.post("/admin/register/mentor")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mentordto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
	}
	
	@Test
	public void testMentorUpdate() throws Exception{
		MentorUpdateDto  mentorUpdateDto = MentorUpdateDto.builder()
				.employeeId("Ty-112").mentorEmailId("xyz@gmail.com")
				.mentorName("Ram").skills(Lists.newArrayList())
				.skills(Lists.newArrayList())
				.batch(new Batch()).build();
		 Mockito.when(adminService.updateMentor(Mockito.any(),Mockito.any())).thenReturn(true);
		 mockMvc.perform(MockMvcRequestBuilders.put("/admin/update/mentor/Ty-112")
		 		.accept(MediaType.APPLICATION_JSON)
		 		.contentType(MediaType.APPLICATION_JSON)
		 		.content(objectMapper.writeValueAsString(mentorUpdateDto)))
		 		.andExpect(MockMvcResultMatchers.status().isOk())
		 		.andReturn()
		 		.getResponse();
	}

	@Test
	public void testMentorDelete() throws Exception {
		Mockito.when(adminService.deleteMentor("123")).thenReturn(Optional.ofNullable(true));
		mockMvc.perform(MockMvcRequestBuilders.put("/admin/delete/mentor/123"))
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	public void testBatchRegister() throws Exception {
	   BatchRegisterDto batchdto =
			     BatchRegisterDto.builder().batchId("Ty-B-01").batchName("Toppers")
			     .batchStatus(BatchStatus.INPROGRESS)
			     .mentorName("ravi")
			     .startDate(LocalDate.of(2022, 02, 11))
				 .endDate(LocalDate.of(2023, 03, 14))
				 .technologies(Lists.newArrayList())
				 .build();
		 Mockito.when(adminService.registerBatch(Mockito.any())).thenReturn(Optional.ofNullable("Ty-B-01"));
		 mockMvc.perform(MockMvcRequestBuilders.post("/admin/register/batch")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(batchdto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
	}
	
	@Test
	public void testBatchUpdate() throws Exception{
		
		BatchUpdateDto  batchUpdateDto = BatchUpdateDto.builder()
				.batchName("1st batch").batchStatus(BatchStatus.TOBESTARTED)
				.batchId("Ty-B-01")
				.startDate(LocalDate.of(2022, 12, 21))
				.endDate(LocalDate.of(2023, 12, 21))
				.mentorName("Naresh")
				.technologies(Lists.newArrayList())
				.build();
		 Mockito.when(adminService.updateBatch(Mockito.any(),Mockito.any())).thenReturn(Optional.ofNullable(true));
		 mockMvc.perform(MockMvcRequestBuilders.put("/admin/update/batch/Ty-B-01")
		 		.accept(MediaType.APPLICATION_JSON)
		 		.contentType(MediaType.APPLICATION_JSON)
		 		.content(objectMapper.writeValueAsString(batchUpdateDto)))
		 		.andExpect(MockMvcResultMatchers.status().isOk())
		 		.andReturn()
		 		.getResponse();
	}
	
	@Test
	public void testBatchDelete() throws Exception {
		Mockito.when(adminService.deleteBatch("123")).thenReturn(Optional.ofNullable(true));
		mockMvc.perform(MockMvcRequestBuilders.put("/admin/delete/batch/123"))
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testSearchEmployee() throws Exception{
		MentorDto mentordto =
				 MentorDto.builder().employeeId("123").mentorEmailId("xyz@gmail.com")
				.mentorName("Ajay").status(Status.ACTIVE).skills(Lists.newArrayList()).build();
		 Mockito.when(adminService.searchEmployee(Mockito.any())).thenReturn(Optional.ofNullable(mentordto));
		 mockMvc.perform(MockMvcRequestBuilders.get("/admin/search/123"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
	}
	
	@Test
	public void testApprove() throws Exception{
		ApproveDto approveDto =
				ApproveDto.builder().batchId("Ty-B-01").batchName("Toppers")
			   .build();
		RequestListMessageDto requestListMessageDto = 
				RequestListMessageDto.builder().employeeEmail("xyz@abc.com").message("send mail").build();
		Mockito.when(adminService.approve(Mockito.any(),Mockito.any())).thenReturn(Optional.ofNullable(requestListMessageDto));
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/approve/ty-001","ty-001")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(approveDto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
		    	.andReturn()
				.getResponse()
				.getContentAsString();
	}
	
	@Test
	public void testReject() throws Exception{
		RejectDto rejectDto =
				RejectDto.builder().reason("Not eligible")
			   .build();
		RequestListMessageDto requestListMessageDto = 
				RequestListMessageDto.builder().employeeEmail("xyz@abc.com").message("send mail").build();
		Mockito.when(adminService.approve(Mockito.any(),Mockito.any())).thenReturn(Optional.ofNullable(requestListMessageDto));
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/reject/ty-001","ty-001")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(rejectDto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
		    	.andReturn()
				.getResponse()
				.getContentAsString();
	}
	
	@Test
	public void testBatchList() throws Exception{
		BatchRegisterDto batchdto =
			     BatchRegisterDto.builder().batchId("Ty-B-01").batchName("Toppers")
			     .batchStatus(BatchStatus.INPROGRESS)
			     .mentorName("ravi")
			     .startDate(LocalDate.of(2022, 02, 11))
				 .endDate(LocalDate.of(2023, 03, 14))
				 .technologies(Lists.newArrayList())
				 .build();
		List<BatchRegisterDto> batchDtos = Lists.newArrayList();
       batchDtos.add(batchdto);
	   	 Mockito.when(adminService.batchList()).thenReturn(Optional.ofNullable(batchDtos));
		 mockMvc.perform(MockMvcRequestBuilders.get("/admin/list/batch"))
		   		.andExpect(MockMvcResultMatchers.status().isOk())
		   		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testMentorList() throws Exception{
		MentorDto mentordto =
				 MentorDto.builder().employeeId("123").mentorEmailId("xyz@gmail.com")
				.mentorName("Ajay").status(Status.ACTIVE).skills(Lists.newArrayList()).build();
		List<MentorDto> mentorDtos = Lists.newArrayList();
		mentorDtos.add(mentordto);
	   	 Mockito.when(adminService.mentorList()).thenReturn(Optional.ofNullable(mentorDtos));
		 mockMvc.perform(MockMvcRequestBuilders.get("/admin/list/mentor"))
		   		.andExpect(MockMvcResultMatchers.status().isOk())
		   		.andDo(MockMvcResultHandlers.print());
	}
}
