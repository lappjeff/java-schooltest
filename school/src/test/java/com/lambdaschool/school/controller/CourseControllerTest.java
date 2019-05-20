package com.lambdaschool.school.controller;

import com.lambdaschool.school.service.CourseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(value = CourseController.class)
class CourseControllerTest
{

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CourseService courseService;


	@BeforeEach
	void setUp()
	{
	}

	@AfterEach
	void tearDown()
	{
	}

	@Test
	void listAllCourses()
	{
	}

	@Test
	void getCountStudentsInCourses()
	{
	}

	@Test
	void deleteCourseById()
	{
	}
}