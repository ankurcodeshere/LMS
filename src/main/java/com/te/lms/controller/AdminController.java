package com.te.lms.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.dto.BatchRegisterDto;
import com.te.lms.dto.BatchUpdateDto;
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
		sendEmail.sendEmail("technoelevateproject@gmail.com", mentorDto.getMentorEmailId(),
				"Welcome to the team"
				,"You are now registered as a mentor in the team of techno elevate. \n" + mentor.get());
		return new ApiResponse<String>("registration successful", "", "You will receive credentials in your email");
	}

//	@GetMapping(path = "/search/{empId}")
//	public ApiResponse<String> searchEmployee() 

	@PutMapping(path = "/update/mentor/{empId}")
	public ApiResponse<String> updateMentor(@PathVariable(name = "empId") String empId,
			@RequestBody MentorUpdateDto mentorUpdateDto) {
		Boolean isUpdated = adminService.updateMentor(empId, mentorUpdateDto);
		return null;
	}

	@PutMapping(path = "/apdate/batch/{batchId}")
	public ApiResponse<String> updateBatch(@PathVariable(name = "batchId") String batchId,
			@RequestBody BatchUpdateDto batchUpdateDto) {
		Optional<Boolean> isUpdated = adminService.updateBatch(batchUpdateDto, batchId);
		if (isUpdated.get()) {
			return new ApiResponse<String>("Batch was successfully updated", "",
					"Batch with batch id  '" + batchId + "' got updated.");
		}
		return new ApiResponse<String>("Updation is unsuccessfull", "",
				"Batch with batch id  '" + batchId + "' is not found in the database.");
	}

	@DeleteMapping(path = "/delete/mentor/{empId}")
	public ApiResponse<String> deleteMentor(@PathVariable(name = "empId") String empId) {
		Optional<Boolean> optionalMentor = adminService.deleteMentor(empId);
		if (optionalMentor.get() == true) {
			return new ApiResponse<String>("record was successfully deleted", "",
					"mentor with employee id  '" + empId + "' got deleted.");
		}
		return new ApiResponse<String>("Deletion is unsuccessfull", "",
				"mentor with employee id  '" + empId + "' is not found in the database.");
	}

	@PutMapping(path = "/delete/batch/{batchId}")
	public ApiResponse<String> deleteBatch(@PathVariable(name = "batchId") String batchId) {
		Optional<Boolean> optionalMentor = adminService.deleteBatch(batchId);
		if (optionalMentor.get() == true) {
			return new ApiResponse<String>("record was successfully deleted", "",
					"batch with batch id  '" + batchId + "' got deleted.");
		}
		return new ApiResponse<String>("Deletion is unsuccessfull", "",
				"batch with batch id  '" + batchId + "' is not found in the database.");
	}

	@PostMapping(path = "/register/batch")
	public ApiResponse<String> registerBatch(@RequestBody BatchRegisterDto batchRegisterDto) {
		Optional<String> batch = adminService.registerBatch(batchRegisterDto);
		return new ApiResponse<String>("registration successful", "",
				"Batch madded in the database." + batchRegisterDto);
	}

}
