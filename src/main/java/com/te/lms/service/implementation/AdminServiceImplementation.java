package com.te.lms.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.te.lms.dto.BatchRegisterDto;
import com.te.lms.dto.MentorDeleteDto;
import com.te.lms.dto.MentorDto;
import com.te.lms.dto.MentorUpdateDto;
import com.te.lms.dto.TechnologiesDto;
import com.te.lms.entity.AppUser;
import com.te.lms.entity.Batch;
import com.te.lms.entity.Mentor;
import com.te.lms.entity.Roles;
import com.te.lms.entity.Technologies;
import com.te.lms.repository.AppUserRepository;
import com.te.lms.repository.BatchRepository;
import com.te.lms.repository.EmployeeRepository;
import com.te.lms.repository.MentorRepository;
import com.te.lms.repository.RoleRepository;
import com.te.lms.service.AdminService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService{
	
	private final MentorRepository mentorRepository;
	private final BatchRepository batchRepository; 
	private final RoleRepository roleRepository;
	private final AppUserRepository appUserRepository;
	
	@Override
	public Optional<String> registerMentor(MentorDto mentorDto) {
		Mentor mentor = new Mentor();
		BeanUtils.copyProperties(mentorDto, mentor);
		
		List<Technologies> technologies = Lists.newArrayList();
		
		for (TechnologiesDto technologies2 : mentorDto.getSkills()) {
			Technologies technologies3 = new Technologies();
			BeanUtils.copyProperties(technologies2, technologies3);
			technologies.add(technologies3);
			technologies3.getMentor().add(mentor);
		}
		Optional<Roles> role = roleRepository.findByRoleName("ROLE_MENTOR");
		if(role.isPresent()) {
			AppUser appUser = AppUser.builder().username(mentor.getEmployeeId()).password("12345@Abc").roles(Lists.newArrayList()).build();
			appUser.getRoles().add(role.get());
			Roles roles =Roles.builder().roleName("ROLE_MENTOR").appUsers(Lists.newArrayList()).build();
			appUserRepository.save(appUser);
			mentor.setSkills(technologies);
			mentorRepository.save(mentor);
			return Optional.ofNullable("username = "+appUser.getUsername()+"\n"+"password = "+appUser.getPassword());
		}
		return null;
		
	}
	@Override
	public Optional<Integer> registerBatch(BatchRegisterDto batchRegisterDto) {
		Batch batch = new Batch();
		BeanUtils.copyProperties(batchRegisterDto, batch);
		
		List <Technologies> technologies = Lists.newArrayList();
		
		for(TechnologiesDto technologiesDto :batchRegisterDto.getTechnologies()) {
			Technologies technologies1 = new Technologies();
			BeanUtils.copyProperties(technologiesDto, technologies1);
			technologies.add(technologies1);
			technologies1.getBatch().add(batch); 
		}
		batch.setTechnologies(technologies);
		batchRepository.save(batch);
		
		return Optional.ofNullable(batch.getBatchId());
	}
	@Override
	public Optional<String> updateMentor(MentorUpdateDto mentorUpdateDto) {
		
		
		return null;
	}
	@Override
	public Optional<Boolean> deleteMentor(MentorDeleteDto mentorDeleteDto) {
		
		return null;
	}
}

