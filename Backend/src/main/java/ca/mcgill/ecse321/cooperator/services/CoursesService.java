package ca.mcgill.ecse321.cooperator.services;

import ca.mcgill.ecse321.cooperator.Utilities;
import ca.mcgill.ecse321.cooperator.dao.CoopPositionRepository;
import ca.mcgill.ecse321.cooperator.dao.CourseRepository;
import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class CoursesService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CoopPositionRepository cpRepository;

    @Transactional
    public Course createCourse(String name) {
        if (!Utilities.checkNotEmpty(name))
            throw new IllegalArgumentException("Cannot add a course with empty name");

        Course course = new Course();
        course.setCourseName(name.trim());
        courseRepository.save(course);
        return course;
    }
    
    @Transactional
    public Course rateCourse(String courseName,int coopId,boolean useful) {
    	if (!Utilities.checkNotEmpty(courseName))
            throw new IllegalArgumentException("course with empty name");
    	
    	Course c =courseRepository.findCourseByCourseName(courseName);
    	CoopPosition cp = cpRepository.findByCoopId(coopId);
    	Set<CoopPosition> cps = new HashSet<>();
    	cps.add(cp);
    	c.setCoopPosition(cps);
    	courseRepository.save(c);
    	return c;
    }

    @Transactional
    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Transactional
    public Course getCourseByCourseName(String name) {
        return courseRepository.findCourseByCourseName(name);
    }

    @Transactional
    public Course getCourseByCourseId(int id) {
        return courseRepository.findByCourseId(id);
    }
}