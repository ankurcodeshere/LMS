package com.te.lms.service;
import java.util.Optional;

import com.te.lms.dto.BatchRegisterDto;
import com.te.lms.dto.BatchUpdateDto;
import com.te.lms.dto.MentorDeleteDto;
import com.te.lms.dto.MentorDto;
import com.te.lms.dto.MentorUpdateDto;

public interface AdminService {

	Optional<String> registerMentor(MentorDto mentorDto);

	Optional<String> registerBatch(BatchRegisterDto batchRegisterDto);
	
	Boolean updateMentor(String empId, MentorUpdateDto mentorUpdateDto);

	Optional<Boolean> deleteMentor(String empId);

	Optional<Boolean> deleteBatch(String batchId);

	Optional<Boolean> updateBatch(BatchUpdateDto batchUpdateDto, String batchId);


}
