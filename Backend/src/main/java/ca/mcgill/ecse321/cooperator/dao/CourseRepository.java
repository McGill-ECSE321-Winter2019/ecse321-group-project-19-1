package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.Course;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer>{

    List<Course> findByCourseName(String courseName);

}