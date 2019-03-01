package ca.mcgill.ecse321.cooperator.dao;

import ca.mcgill.ecse321.cooperator.model.Employer;
import org.springframework.data.repository.CrudRepository;

public interface EmployerRepository extends CrudRepository<Employer, Integer> {
    Employer findById(int id);
}
