package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplicationTests;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplicationTests.class)
class StudentServiceImplTest
{

	@Autowired
	private StudentService studentService;

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

	//Todo Fix the new student being assigned an id of 0
	@Test
	void save()
	{
		Student student = new Student("Test Student");

		student.getCourses().add(courseService.findCourseByName("Data Science"));
		student.setStudid(1234);

		Student savedStudent = studentService.save(student);

		System.out.println(savedStudent);

		assertNotNull(savedStudent);

		Student foundStudent = studentService.findStudentById(savedStudent.getStudid());

		System.out.println("Found student " + foundStudent);

		assertEquals(student.getStudname(), foundStudent.getStudname());
	}
}