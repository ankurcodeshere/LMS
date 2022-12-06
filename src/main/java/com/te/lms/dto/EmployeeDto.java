package com.te.lms.dto;

import java.time.LocalDate;
import java.util.List;

import com.te.lms.entity.EmployeeBankDetails;
import com.te.lms.entity.EmployeeSecondaryInfo;
import com.te.lms.enums.Designation;
import com.te.lms.enums.EmployeeStatus;
import com.te.lms.enums.Gender;

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
public class EmployeeDto {

	private String employeeId;
	private String employeeName;
	private LocalDate employeeDoj;
	private LocalDate employeeDob;
	private String employeeEmail;
	private String bloodGroup;
	private Designation designation;
	private Gender gender;
	private String employeeNationality;
	private EmployeeStatus employeeStatus;

	private EmployeeSecondaryInfo employeeSecondaryInfo;
	private List<EmployeeEducationInfoDto> employeeEducationInfos;
	private List<EmployeeContactInfoDto> employeeContactInfos;
	private List<EmployeeAddressInfoDto> employeeAddressInfos;
	private EmployeeBankDetails employeeBankDetails;
	private List<EmployeeExperienceInfoDto> employeeExperienceInfos;
	private List<EmployeeTechnicalskillsInfoDto> employeeTechnicalskillsInfos;
}
