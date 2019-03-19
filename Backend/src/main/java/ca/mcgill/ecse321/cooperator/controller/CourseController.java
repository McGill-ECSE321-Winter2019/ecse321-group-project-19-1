package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.dto.CoopPositionDto;
import ca.mcgill.ecse321.cooperator.dto.CourseDto;
import ca.mcgill.ecse321.cooperator.model.Course;
import ca.mcgill.ecse321.cooperator.services.CoopPositionService;
import ca.mcgill.ecse321.cooperator.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.CollationElementIterator;
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
	 *
	 * @param courseName The name of the course
	 * @return A CourseDto representing the newly added course.
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/createCourse", "/createCourse/" })
	public CourseDto createCourse(@RequestParam("courseName") String courseName) throws IllegalArgumentException {
		Course course = coursesService.createCourse(courseName);
		return DtoConverters.convertToDto(course);
	}

	/**
	 * View all courses in the system
	 *
	 * @return a list of CourseDto representing all courses in the system.
	 */
	@GetMapping(value = { "/allCourses", "/allCourses/" })
	public List<CourseDto> getAllCourses() {
		List<CourseDto> coursesDto = new ArrayList<>();
		for (Course course : coursesService.getAllCourses()) {
			coursesDto.add(DtoConverters.convertToDto(course));
		}
		return coursesDto;
	}

	/**
	 * Add a course to the list a useful course for a specific coop
	 *
	 * @param courseId course to be added
	 * @param coopId   the coop to which the course is added
	 * @return a CoopPositionDto representing the modified coop
	 */
	@PostMapping(value = { "/rateCourse", "/rateCourse/" })
	public CoopPositionDto rateCourse(@RequestParam("courseId") Integer courseId,
			@RequestParam(name = "coopId") int coopId) {
		return DtoConverters.convertToDto(coursesService.rateCourse(courseId, coopId));
	}
	
	/**
	 * Delete a course
	 * 
	 * @param cId course id
	 * @return true if success
	 */
	@PostMapping(value = { "/deleteCourse", "/deleteCourse/" })
	public boolean deleteDocument(@RequestParam(name = "courseId") int cId) {
		coursesService.deleteCourse(cId);
		return true;
	}

	/**
	 * Get a sort list of all course based on the usefulness of a course measured by
	 * the number of times it's mentioned across all coop positions.
	 *
	 * @return a list of CourseDto representing the sorted list of courses.
	 */
	@GetMapping(value = { "/ranking", "/ranking/" })
	public List<CourseDto> getCoursesRanking() {
		List<Course> courses = coursesService.getAllCourses();
		Collections.sort(courses, (c1, c2) -> c2.getCoopPosition().size() - c1.getCoopPosition().size());
		List<CourseDto> res = new ArrayList<>();
		for (Course c : courses)
			res.add(DtoConverters.convertToDto(c));
		return res;
	}
}
