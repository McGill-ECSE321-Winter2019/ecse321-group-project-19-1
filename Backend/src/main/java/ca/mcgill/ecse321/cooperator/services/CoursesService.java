package ca.mcgill.ecse321.cooperator.services;

import ca.mcgill.ecse321.cooperator.dao.CourseRepository;
import ca.mcgill.ecse321.cooperator.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CoursesService {

    Boolean CheckNotEmpty(String s) {
        return s != null && !s.equals("") && s.trim().length() > 0;
    }

    @Autowired
    CourseRepository courseRepository;

    @Transactional
    public Course createCourse(String name) {
        if (!CheckNotEmpty(name))
            throw new IllegalArgumentException("Cannot add a course with empty name");

        Course course = new Course();
        course.setCourseName(name.trim());
        courseRepository.save(course);
        return course;
    }

    @Transactional
    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Transactional
    public Course getCourseByCourseName(String name) {
        return courseRepository.findCourseByCourseName(name);
    }
}