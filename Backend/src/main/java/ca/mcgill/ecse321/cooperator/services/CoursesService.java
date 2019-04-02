package ca.mcgill.ecse321.cooperator.services;

import ca.mcgill.ecse321.cooperator.Utilities;
import ca.mcgill.ecse321.cooperator.dao.CoopPositionRepository;
import ca.mcgill.ecse321.cooperator.dao.CourseRepository;
import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.Course;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Parser;
import java.util.List;

@Service
public class CoursesService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CoopPositionRepository cpRepository;

    /**
     * Create a new course in the system.
     *
     * @param courseName The name of the course
     * @return A Course representing the newly added course.
     * @throws IllegalArgumentException
     */

    @Transactional
    public Course createCourse(String name) {
        if (!Utilities.checkNotEmpty(name))
            throw new IllegalArgumentException("Cannot add a course with empty name");

        Course course = new Course();
        course.setCourseName(name.trim());
        courseRepository.save(course);
        return course;
    }

    /**
     * Add a course to the list a useful course for a specific coop
     *
     * @param courseId course to be added
     * @param coopId   the coop to which the course is added
     * @return a CoopPosition representing the modified coop
     */

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

    /**
     * View all courses in the system
     *
     * @return a list of Course representing all courses in the system.
     */

    @Transactional
    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    /**
     * Get course with a specific name
     * 
     * @params name of a course
     * @return A course with the specific name
     */

    @Transactional
    public Course getCourseByCourseName(String name) {
        return courseRepository.findCourseByCourseName(name);
    }

    /**
     * Get course with a specific id
     * 
     * @params id the id of a course
     * @return A course with the specific id
     */

    @Transactional
    public Course getCourseByCourseId(int id) {
        return courseRepository.findByCourseId(id);
    }

    /**
     * Delete a course
     * 
     * @param cId course id
     * @return true if success
     */

    @Transactional
    public boolean deleteCourse(int courseId) {
        Course c = courseRepository.findByCourseId(courseId);
        if (c == null) {
            throw new NullPointerException("No such course.");
        }
        courseRepository.deleteById(courseId);
        return true;
    }
}