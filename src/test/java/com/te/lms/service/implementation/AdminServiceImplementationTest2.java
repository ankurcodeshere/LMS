
package com.te.lms.service.implementation;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.te.lms.repository.MentorRepository;

@SpringBootTest
public class AdminServiceImplementationTest2 {

	@Mock
	MentorRepository mentorRepository;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testMentorList() {
		fail("Not yet implemented");
	}

}
