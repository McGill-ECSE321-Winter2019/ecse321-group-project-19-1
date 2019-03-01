package ca.mcgill.ecse321.cooperator.dao;

import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.Status;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.TermInstructor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoopPositionRepository extends CrudRepository<CoopPosition, Integer> {
    List<CoopPosition> findCoopPositionByStudent(Student student);

    List<CoopPosition> findCoopPositionByTerm(String term);

    List<CoopPosition> findCoopPositionByStatus(Status status);

    List<CoopPosition> findCoopPositionByTermInstructor(TermInstructor termInstructor);

    CoopPosition findCoopPositionByCoopId(Integer coopId);
}