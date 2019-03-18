package ca.mcgill.ecse321.cooperator.services;

import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.model.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployerService {

    @Autowired
    EmployerRepository employerRepository;

    public Employer createEmployer() {
        Employer employer = new Employer();
        employerRepository.save(employer);
        return employer;
    }

    @Transactional
    public Employer getById(int id) {
        Employer employer = employerRepository.findById(id);
        return employer;
    }

    @Transactional
    public List<Employer> getAllEmployers() {
        return (List<Employer>) employerRepository.findAll();
    }
    
    @Transactional
    public void deleteEmployer(int employerId) {
    	Employer e = employerRepository.findById(employerId);
    	if (e == null) {
    		throw new NullPointerException("No such employer.");
    	}
    	employerRepository.deleteById(employerId);
    }
}
