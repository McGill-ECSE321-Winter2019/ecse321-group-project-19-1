package ca.mcgill.ecse321.cooperator.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.*;

import ca.mcgill.ecse321.cooperator.dao.CourseRepository;
import ca.mcgill.ecse321.cooperator.model.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.hibernate.internal.util.collections.ArrayHelper.toList;


@Service
public class ProgramManagerService {

    @Autowired
    CourseRepository courseRepository;

    @Transactional
    public Course createCourse(String name) {
        Course course = new Course();
        course.setCourseName(name);
        courseRepository.save(course);
        return course;
    }

    @Transactional
    public List<Course> getAllCourses(){
        return (List<Course>)courseRepository.findAll();
    }
}