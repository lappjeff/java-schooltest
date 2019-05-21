package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/students")
public class StudentController
{
	@Autowired
	private StudentService studentService;

	@PostMapping(value = "/add", consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<?> addNewStudent(@Valid @RequestBody
												   Student newStudent)
	{
		newStudent = studentService.save(newStudent);

		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}
}
