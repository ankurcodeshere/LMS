package com.te.lms.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.dto.BatchRegisterDto;
import com.te.lms.dto.MentorDeleteDto;
import com.te.lms.dto.MentorDto;
import com.te.lms.dto.MentorUpdateDto;
import com.te.lms.email.SendEmail;
import com.te.lms.response.ApiResponse;
import com.te.lms.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
public class AdminController {

	private final AdminService adminService;
	private final SendEmail sendEmail;

	@PostMapping(path = "/register/mentor")
	public ApiResponse<String> registerMentor(@RequestBody MentorDto mentorDto) {
		Optional<String> mentor = adminService.registerMentor(mentorDto);
		sendEmail.sendEmail("technoelevateproject@gmail.com", mentorDto.getMentorEmailId(), "Welcome to the team",
				"You are now registered as a mentor in the team of techno elevate. \n"
				+ mentor.get());
		return new ApiResponse<String>("registration successful", "", "You will receive credentials in your email");
	}

	@PostMapping(path="/update/mentor")
	public ApiResponse<String> updateMentor(@RequestBody MentorUpdateDto mentorUpdateDto) {
		Optional<String> mentor = adminService.updateMentor(mentorUpdateDto);
		return null;
	}

	@PostMapping(path="/delete/mentor")
	public ApiResponse<String> deleteMentor(@RequestBody MentorDeleteDto mentorDeleteDto) {
		Optional<Boolean> mentor = adminService.deleteMentor(mentorDeleteDto);
		return null;
	}

	@PostMapping(path = "/register/batch")
	public ApiResponse<String> registerBatch(@RequestBody BatchRegisterDto batchRegisterDto) {
		Optional<Integer> batch = adminService.registerBatch(batchRegisterDto);
		return new ApiResponse<String>("registration successful", "", "You will receive credentials in your email");
	}

}
