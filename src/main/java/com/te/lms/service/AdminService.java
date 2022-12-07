package com.te.lms.service;
import java.util.Optional;

import com.te.lms.dto.BatchRegisterDto;
import com.te.lms.dto.MentorDeleteDto;
import com.te.lms.dto.MentorDto;
import com.te.lms.dto.MentorUpdateDto;

public interface AdminService {

	Optional<String> registerMentor(MentorDto mentorDto);

	Optional<Integer> registerBatch(BatchRegisterDto batchRegisterDto);

	Optional<String> updateMentor(MentorUpdateDto mentorUpdateDto);

	Optional<Boolean> deleteMentor(MentorDeleteDto mentorDeleteDto);

}
