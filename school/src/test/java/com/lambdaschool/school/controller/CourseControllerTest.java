package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.SchoolApplicationTests;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.CourseService;
import com.lambdaschool.school.service.InstructorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
//import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseController.class)
@ContextConfiguration(classes = SchoolApplicationTests.class)
class CourseControllerTest
{

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CourseService courseService;

	@MockBean
	private InstructorService instructorService;

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
	void addNewCourse() throws Exception
	{
		String url = "/courses/course/add";

		Instructor instructor = instructorService.findById(1);

		Course course = new Course("Test Course", instructor);

		course.getStudents().add(new Student("Test Jim"));

		Mockito.when(courseService.save(any(Course.class))).thenReturn(course);

		course.setCourseid(5341);

		ObjectMapper mapper = new ObjectMapper();
		String courseStr = mapper.writeValueAsString(course);

		RequestBuilder rb = MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).
				content(courseStr).accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());


	}

	@Test
	void deleteCourseById()
	{
	}
}