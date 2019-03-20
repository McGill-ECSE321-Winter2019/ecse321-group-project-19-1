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
    private boolean EXTRACT_DATA = false;
    @Autowired
    EmployerRepository employerRepository;

    EmployerService(){
        if(EXTRACT_DATA) {
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(3000);
                        // We don't have a method for that yet!
                        JSONArray jaResponse = Utilities.sendRequestArray("GET", Utilities.BASE_URL_STUDENTVIEW, "/");
                        if (jaResponse != null) {
                            System.out.println(jaResponse);
                        }
                    } catch (Exception e) {
                        System.out.println("Course extractor thread failed");
                    }
                }
            }).start();
        }
    }

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
    public boolean deleteEmployer(int employerId) {
    	Employer e = employerRepository.findById(employerId);
    	if (e == null) {
    		throw new NullPointerException("No such employer.");
    	}
    	employerRepository.deleteById(employerId);
    	return true;
    }
}
