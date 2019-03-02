package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.dto.CourseDto;
import ca.mcgill.ecse321.cooperator.model.Course;
import ca.mcgill.ecse321.cooperator.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class CourseController {
    @Autowired
    CoursesService coursesService;

    // create course
    @PostMapping(value = {"/createCourse", "/createCourse/"})
    public CourseDto createCourse(@RequestParam("courseName") String courseName) throws IllegalArgumentException {
        Course course = coursesService.createCourse(courseName);
        return DtoConverters.convertToDto(course);
    }

    // view all courses
    @GetMapping(value = {"/courses", "/courses/"})
    public List<CourseDto> getAllCourses() {
        List<CourseDto> coursesDto = new ArrayList<>();
        for (Course course : coursesService.getAllCourses()) {
            coursesDto.add(DtoConverters.convertToDto(course));
        }
        return coursesDto;
    }

    // getting list of ranked courses
    @GetMapping(value = {"/ranking", "/ranking/"})
    public List<CourseDto> getCoursesRanking() {
        List<Course> courses = coursesService.getAllCourses();
        Map<Integer, CourseDto> courseDtoMap = new TreeMap<>(Collections.reverseOrder());
        for (Course c : courses)
            courseDtoMap.put(c.getCoopPosition().size(), DtoConverters.convertToDto(c));

        return new ArrayList<>(courseDtoMap.values());
    }
}
