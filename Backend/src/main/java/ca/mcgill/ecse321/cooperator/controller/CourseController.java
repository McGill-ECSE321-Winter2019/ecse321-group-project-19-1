package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.dto.CourseDto;
import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.Course;
import ca.mcgill.ecse321.cooperator.services.CoopPositionService;
import ca.mcgill.ecse321.cooperator.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class CourseController {
    @Autowired
    CoursesService coursesService;
    
    @Autowired
    CoopPositionService cpService;

    /**
     * Create a new course in the system.
     * @param courseName The name of the course
     * @return A CourseDto representing the newly added course.
     * @throws IllegalArgumentException
     */
    @PostMapping(value = {"/createCourse", "/createCourse/"})
    public CourseDto createCourse(@RequestParam("courseName") String courseName) throws IllegalArgumentException {
        Course course = coursesService.createCourse(courseName);
        return DtoConverters.convertToDto(course);
    }

    /**
     * View all courses in the system
     * @return a list of CourseDto representing all courses in the system.
     */
    @GetMapping(value = {"/courses", "/courses/"})
    public List<CourseDto> getAllCourses() {
        List<CourseDto> coursesDto = new ArrayList<>();
        for (Course course : coursesService.getAllCourses()) {
            coursesDto.add(DtoConverters.convertToDto(course));
        }
        return coursesDto;
    }
    
    /**
     * Rate a course
     * @return the CourseDto that has been rated
     */
    @PostMapping(value= {"/rateCourse","/rateCourse/"})
    public CourseDto rateCourse(@RequestParam("courseName") String courseName, @RequestParam(name="coopId") int coopId,
    		@RequestParam(name="useful")Boolean useful) {
    	Course c = coursesService.getCourseByCourseName(courseName);
    	CoopPosition cp = cpService.getById(coopId);
    	Set<CoopPosition> cps = new HashSet<>();
    	if(useful) {
    	cps.add(cp);
    	c.setCoopPosition(cps);
    	}
    	return DtoConverters.convertToDto(c);
    }
    

    /**
     * Get a sort list of all course based on the usefulness of a course measured by the number of times it's
     * mentioned across all coop positions.
     * @return a list of CourseDto representing the sorted list of courses.
     */
    @GetMapping(value = {"/ranking", "/ranking/"})
    public List<CourseDto> getCoursesRanking() {
        List<Course> courses = coursesService.getAllCourses();
        Map<Integer, CourseDto> courseDtoMap = new TreeMap<>(Collections.reverseOrder());
        for (Course c : courses)
            courseDtoMap.put(c.getCoopPosition().size(), DtoConverters.convertToDto(c));

        return new ArrayList<>(courseDtoMap.values());
    }
}
