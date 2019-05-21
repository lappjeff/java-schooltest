package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "studentService")
public class StudentServiceImpl implements StudentService
{
    @Autowired
    private StudentRepository studrepos;

    @Autowired
    private CourseService courseService;

    @Transactional
    @Override
    public Student save(Student student)
    {
        Student newStudent = new Student();

        newStudent.setStudname(student.getStudname());

        for(Course c: student.getCourses())
        {

            if(courseService.findCourseByName(c.getCoursename()) != null)
            {
                newStudent.getCourses().add(c);
            } else
            {
                Course newCourse = new Course(c.getCoursename(), c.getInstructor());
                courseService.save(newCourse);
            }
        }

        return newStudent;
    }

    @Override
    public Student findStudentById(long id) throws EntityNotFoundException
    {
        return studrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }
}
