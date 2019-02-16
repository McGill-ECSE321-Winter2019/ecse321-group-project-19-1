package ca.mcgill.ecse321.cooperator.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.RequiredDocument;

public interface RequiredDocumentRepository extends CrudRepository<RequiredDocument, Integer> {
	RequiredDocument findRequiredDocumentBydocumentId(int id);
	RequiredDocument findRequiredDocumentByName(String name);
	List<RequiredDocument> findRequiredDocumentByDueDate(Date dueDate);
	List<RequiredDocument> findRequiredDocumentByCoopPosition(CoopPosition coopPosition);
}
