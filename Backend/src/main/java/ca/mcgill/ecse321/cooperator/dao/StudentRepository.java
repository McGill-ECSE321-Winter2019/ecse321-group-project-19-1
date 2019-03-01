package ca.mcgill.ecse321.cooperator.dao;

import ca.mcgill.ecse321.cooperator.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    Student findById(int id);

    List<Student> findStudentByProblematic(Boolean problematic);

}
