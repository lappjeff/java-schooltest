package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.CourseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;

@WebMvcTest(value = CourseController.class)
class CourseControllerTest
{

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CourseService courseService;

	private ArrayList<Course> courses = new ArrayList<>();

	@BeforeEach
	void setUp()
	{
		Instructor instructor1 = new Instructor("Walter White");

		Course course1 = new Course("Advanced Meth-Matics");

		Student stud1 = new Student("Jesse Pinkman");

		course1.getStudents().add(stud1);
		instructor1.getCourses().add(course1);

		courses.add(course1);
	}

	@AfterEach
	void tearDown()
	{
	}

	@Test
	void listAllCourses() throws Exception
	{
		String url = "/courses/courses";

		Mockito.when(courseService.findAll()).thenReturn(courses);

		RequestBuilder rb = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);

		MvcResult r = mockMvc.perform(rb).andReturn();

		String stringResult = r.getResponse().getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		String expectedResult = mapper.writeValueAsString(courses);

		assertEquals(expectedResult, stringResult);
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