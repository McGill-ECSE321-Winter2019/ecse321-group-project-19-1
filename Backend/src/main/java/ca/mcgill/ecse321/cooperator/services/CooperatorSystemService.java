package ca.mcgill.ecse321.cooperator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.cooperator.dao.CooperatorManagerRepository;
import ca.mcgill.ecse321.cooperator.model.CooperatorManager;


@Service
public class CooperatorSystemService {
	@Autowired
    CooperatorManagerRepository systemRepository;
 
	private Boolean CheckNotEmpty(String s) {
        return s != null && !s.equals("") && s.trim().length() > 0;
    }
	
    public CooperatorManager createSystem(String name) {
        if (!CheckNotEmpty(name))
            throw new IllegalArgumentException("Cannot add a system with empty name.");
    	CooperatorManager cooperatorSystem = new CooperatorManager();
    	cooperatorSystem.setSystemName(name);
    	systemRepository.save(cooperatorSystem);
        return cooperatorSystem;
    }

 	@Transactional
 	public CooperatorManager getSystem(String name) {
 		CooperatorManager cooperatorSystem = systemRepository.findCooperatorManagerBySystemName(name);
 		return cooperatorSystem;
 	}
    @Transactional
    public List<CooperatorManager> getAllSystems(){
        return (List<CooperatorManager>)systemRepository.findAll();
    }
}
