package ca.mcgill.ecse321.cooperator.dao;

import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.RequiredDocument;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface RequiredDocumentRepository extends CrudRepository<RequiredDocument, Integer> {
    RequiredDocument findById(int id);

    RequiredDocument findRequiredDocumentByName(String name);

    List<RequiredDocument> findRequiredDocumentByDueDate(Date dueDate);

    List<RequiredDocument> findRequiredDocumentByCoopPosition(CoopPosition coopPosition);
}
