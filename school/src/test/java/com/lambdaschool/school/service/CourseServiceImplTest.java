package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplicationTests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.persistence.EntityNotFoundException;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplicationTests.class)
class CourseServiceImplTest
{

	@Autowired
	private CourseService courseService;

	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	@AfterEach
	void tearDown()
	{
	}

	@Test
	void findAll()
	{
		assertEquals(6, courseService.findAll().size());
	}

	@Test
	void getCountStudentsInCourse()
	{
	}

	@Test
	void findCourseById()
	{
		assertEquals("Data Science", courseService.findCourseById(1).getCoursename());
	}

	@Test
	void deleteNotFound()
	{
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			courseService.delete(3291);
		});

		assertEquals(6, courseService.findAll().size());

	}

	@Test
	void deleteFound()
	{
		courseService.delete(1);
		assertEquals(5, courseService.findAll().size());
	}
}