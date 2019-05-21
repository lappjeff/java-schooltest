package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.SchoolApplicationTests;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.CourseService;
import com.lambdaschool.school.service.InstructorService;
import com.lambdaschool.school.service.StudentService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.sql.SQLOutput;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = SchoolApplicationTests.class)

public class CourseControllerIntegrationTest
{
	@Autowired
	private WebApplicationContext webApplicationContext;


	@Before
	public void init()
	{

		RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

	}

	@Test
	public void testServerResponseTime() throws Exception
	{
		given().when().get("/courses/courses").then().time(lessThan(5000L));
	}

	@Test
	public void testPostCourse() throws Exception
	{
		Course course = new Course("Test Course");

		ObjectMapper mapper = new ObjectMapper();

		String courseStr = mapper.writeValueAsString(course);

		System.out.println(courseStr);

		given().contentType("application/json").body(courseStr).when().post("/courses/course/add").then().statusCode(201);
	}

}
