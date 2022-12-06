package com.te.lms.dto;

import java.time.LocalDate;
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
public class BatchRegisterDto {
	private String batchName;
	private String mentorName;
	private List<TechnologiesDto> technologies;
	private LocalDate startDate;
	private LocalDate endDate;
}
