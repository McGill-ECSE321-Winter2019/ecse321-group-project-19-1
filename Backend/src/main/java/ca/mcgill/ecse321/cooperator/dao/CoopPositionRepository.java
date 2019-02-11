package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.Status;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.TermInstructor;


import java.util.List;

public interface CoopPositionRepository extends CrudRepository<CoopPosition, Integer>{

    List<CoopPosition> findByStudent(Student student);
    List<CoopPosition> findByTerm(String term);
    List<CoopPosition> findByStatus(Status status);
    List<CoopPosition> findByTermInstructor(TermInstructor termInstructor);

}