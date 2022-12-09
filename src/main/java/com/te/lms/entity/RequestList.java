package com.te.lms.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "request_list")
public class RequestList {
	@Id
	private String employeeId;
	private String employeeName;
	private LocalDate yearOfPassing;
	private Double percentage;
	private Integer yearsOfExperience;
	private String contactNo;	
}
