package ca.mcgill.ecse321.cooperator.services;

import ca.mcgill.ecse321.cooperator.Utilities;
import ca.mcgill.ecse321.cooperator.dao.CoopPositionRepository;
import ca.mcgill.ecse321.cooperator.dao.CourseRepository;
import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.Course;
import ca.mcgill.ecse321.cooperator.model.Employer;

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
    public CoopPosition rateCourse(int courseId, int coopId) {
        Course c = courseRepository.findByCourseId(courseId);
        CoopPosition cp = cpRepository.findByCoopId(coopId);
        cp.addUsefulCourse(c);
        c.addAssociatedCoop(cp);
        courseRepository.save(c);
        cpRepository.save(cp);
        return cp;
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
    
    @Transactional
    public void deleteCourse(int courseId) {
    	Course c = courseRepository.findByCourseId(courseId);
    	if (c == null) {
    		throw new NullPointerException("No such course.");
    	}
    	courseRepository.deleteById(courseId);
    }
}