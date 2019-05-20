package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.CourseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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