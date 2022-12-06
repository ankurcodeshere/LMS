package com.te.lms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.te.lms.enums.AccountType;

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
public class EmployeeBankDetails {
	
	@Id
	private String accountNo;
	private String bankName;
	@Enumerated
	private AccountType accountType;
	private String ifscCode;
	private String branch;
	private String state;
	
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "employeeBankDetails")
	private Employee employee;
}
