package com.te.lms.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MentorDto {
	private String mentorName;
	private String mentorEmailId;
	private String employeeId;
	private List<TechnologiesDto> skills;
}
