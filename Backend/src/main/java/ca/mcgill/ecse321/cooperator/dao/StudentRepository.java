package ca.mcgill.ecse321.cooperator.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

	Student findById(int id);
	List<Student> findStudentByProblematic(Boolean problematic);
	
}
