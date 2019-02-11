package ca.mcgill.ecse321.cooperator.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.RequiredDocument;
import ca.mcgill.ecse321.cooperator.model.Student;

public interface RequiredDocumentRepository extends CrudRepository<RequiredDocument, Integer> {
	RequiredDocument findById(int id);
	RequiredDocument findByName(String name);
	List<RequiredDocument> findByDueDate(Date dueDate);
	List<RequiredDocument> findByStudentAndCoopPosition(Student s, CoopPosition cp);
}
