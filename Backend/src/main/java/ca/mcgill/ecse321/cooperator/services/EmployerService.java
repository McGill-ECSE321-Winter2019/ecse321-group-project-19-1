package ca.mcgill.ecse321.cooperator.services;

import ca.mcgill.ecse321.cooperator.Utilities;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.model.Employer;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployerService {
    @Autowired
    EmployerRepository employerRepository;

    /**
	 * Create a new Employer in the system
	 *
	 * @return an Employer representing the newly added Employer
	 */
    public Employer createEmployer() {
        Employer employer = new Employer();
        employerRepository.save(employer);
        return employer;
    }

    /**
     * get a employer by id
     * 
     * @param id of the employer
     * @return the employer with the id
     */

    @Transactional
    public Employer getById(int id) {
        Employer employer = employerRepository.findById(id);
        return employer;
    }

    /**
     * get all employers in the system
     *
     * @return A list of all employers in the system
     */
    @Transactional
    public List<Employer> getAllEmployers() {
        return (List<Employer>) employerRepository.findAll();
    }
    
    /**
	 * deleting an employer by id
	 * 
	 * @param employerId of the employer to remove
	 * @return true = success; false = fail
	 */
    @Transactional
    public boolean deleteEmployer(int employerId) {
    	Employer e = employerRepository.findById(employerId);
    	if (e == null) {
    		throw new NullPointerException("No such employer.");
    	}
    	employerRepository.deleteById(employerId);
    	return true;
    }
}
