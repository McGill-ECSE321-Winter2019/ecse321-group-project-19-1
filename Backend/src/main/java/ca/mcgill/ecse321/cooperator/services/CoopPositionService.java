package ca.mcgill.ecse321.cooperator.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.cooperator.dao.CoopPositionRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.Status;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.TermInstructor;

@Service   
public class CoopPositionService {

	 Boolean CheckNotEmpty(String s) {
	        return s != null && !s.equals("") && s.trim().length() > 0;
	    }
	 @Autowired
	 	CoopPositionRepository coopPositionRepository;
	 
	 @Autowired
	 	StudentRepository studentRepository;
	 
	 @Transactional
	    public CoopPosition createCoopPosition(Date startDate, Date endDate,String description,String location, String term, Student student) {
	        if(!CheckNotEmpty(description))
	            throw new IllegalArgumentException("Cannot add a coop position with empty description");
	        if(!CheckNotEmpty(location))
	            throw new IllegalArgumentException("Cannot add a coop position with empty location");
	        if(!CheckNotEmpty(term))
	            throw new IllegalArgumentException("Cannot add a coop position with empty term");
	        if(startDate == null)
	            throw new IllegalArgumentException("Cannot add a coop position with empty startDate");
	        if(endDate == null)
	            throw new IllegalArgumentException("Cannot add a coop position with empty endDate");
	      
	        
	        CoopPosition cp = new CoopPosition();
	        cp.setDescription(description);
	        cp.setLocation(location);
	        cp.setStartDate(startDate);
	        cp.setEndDate(endDate);
	        cp.setTerm(term);
	        cp.setStudent(student);
	        coopPositionRepository.save(cp);
	        return cp;
	    }
	 
	 @Transactional
	 	public List<CoopPosition> getCoopPositionsByStatus(Status status){
		 List<CoopPosition> coopPositionsByStatus = new ArrayList<>();
		 for(CoopPosition cp: coopPositionRepository.findCoopPositionByStatus(status)) {
		 		coopPositionsByStatus.add(cp);
		 	}
		 	return coopPositionsByStatus;
	    }
	 
	 @Transactional
	    public List<CoopPosition> getCoopPositionsByStudent(Student student){
		 	List<CoopPosition> coopPositionsByStudent = new ArrayList<>();
		 	for(CoopPosition cp: coopPositionRepository.findCoopPositionByStudent(student)) {
		 		coopPositionsByStudent.add(cp);
		 	}
		 	return coopPositionsByStudent;
	    }
	 
	 @Transactional
	 	public List<CoopPosition> getCoopPositionsByTerm(String term){
		 List<CoopPosition> coopPositionsByTerm = new ArrayList<>();
		 for(CoopPosition cp: coopPositionRepository.findCoopPositionByTerm(term)) {
		 		coopPositionsByTerm.add(cp);
		 	}
		 	return coopPositionsByTerm;
	    }
	 
	 @Transactional
	 	public List<CoopPosition> getCoopPositionsByTermInstructor(TermInstructor termInstructor){
		 List<CoopPosition> coopPositionsByTermInstructor = new ArrayList<>();
		 for(CoopPosition cp: coopPositionRepository.findCoopPositionByTermInstructor(termInstructor)) {
		 		coopPositionsByTermInstructor.add(cp);
		 	}
		 	return coopPositionsByTermInstructor;
	    }
	 
	 @Transactional
	    public List<CoopPosition> getAllCoopPositions(){
	        return (List<CoopPosition>)coopPositionRepository.findAll();
	    }
	 
	 
}
