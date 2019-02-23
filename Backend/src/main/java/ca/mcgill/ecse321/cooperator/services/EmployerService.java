package ca.mcgill.ecse321.cooperator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.model.CooperatorManager;
import ca.mcgill.ecse321.cooperator.model.Employer;

@Service
public class EmployerService {
	
	@Autowired
    EmployerRepository employerRepository;
 
    public Employer createEmployer(CooperatorManager sys) {
    	if(sys == null)
            throw new IllegalArgumentException("Cannot add an employer with empty system");
    	Employer employer = new Employer();
    	employer.setCooperatorManager(sys);
    	employerRepository.save(employer);
        return employer;
    }

 	@Transactional
 	public Employer getEmployer(int id) {
 		Employer employer = employerRepository.findById(id);
 		return employer;
 	}
 	
    @Transactional
    public List<Employer> getAllEmployers(){
        return (List<Employer>)employerRepository.findAll();
    }
}
