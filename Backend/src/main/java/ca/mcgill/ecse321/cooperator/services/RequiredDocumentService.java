package ca.mcgill.ecse321.cooperator.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.cooperator.dao.CoopPositionRepository;
import ca.mcgill.ecse321.cooperator.dao.RequiredDocumentRepository;
import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.CooperatorManager;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.EmployerContract;
import ca.mcgill.ecse321.cooperator.model.Form;
import ca.mcgill.ecse321.cooperator.model.Report;
import ca.mcgill.ecse321.cooperator.model.ReportType;
import ca.mcgill.ecse321.cooperator.model.RequiredDocument;
import ca.mcgill.ecse321.cooperator.model.Student;





@Service   
public class RequiredDocumentService {
	
	 private enum RequiredDocumentType {
	        REPORT,
	        EMPLOYER_CONTRACT,
	        FORM
	    }
	 
	 @Autowired
	 	CoopPositionRepository coopPositionRepository;
	 @Autowired
	    RequiredDocumentRepository requiredDocumentRepository;
	 
	 @Transactional
	    public Report createReport(String name, Date dueDate, CoopPosition cp, ReportType type, CooperatorManager sys) {
	        Report report = (Report) createRequiredDocument(name, dueDate,cp, RequiredDocumentType.REPORT, sys);
	        report.setReportType(type);
	        return report;
	    }

	 @Transactional
	    public EmployerContract createEmployerContract(String name, Date dueDate, CoopPosition cp, Employer em, CooperatorManager sys) {
	        if (!CheckNotEmpty(name))
	            throw new IllegalArgumentException("Cannot add a document with empty name.");

	        EmployerContract rdoc = new EmployerContract();
	        
	        if (rdoc != null) { 
	            rdoc.setName(name);
	            rdoc.setDueDate(dueDate);
	            rdoc.setCoopPosition(cp);
	            rdoc.setEmployer(em);
	            rdoc.setCooperatorManager(sys);
	            requiredDocumentRepository.save(rdoc);
	            return rdoc;
	        }
	        throw new IllegalArgumentException("[Internal error] Failed to create a new document.");
	    }
	 
	 @Transactional
	 	public EmployerContract createEmployerContract(String name, Date dueDate, CoopPosition cp, CooperatorManager sys) {
	        return (EmployerContract) createRequiredDocument(name, dueDate,cp, RequiredDocumentType.FORM, sys);
	    }
	 
	 @Transactional
	 	public Form createForm(String name, Date dueDate, CoopPosition cp, CooperatorManager sys) {
	        return (Form) createRequiredDocument(name, dueDate,cp, RequiredDocumentType.FORM, sys);
	    }
	 
	 @Transactional
	 	public List<RequiredDocument> getRequiredDocumentByCoopPosition(CoopPosition cp) {
		 List<RequiredDocument> requiredDocumentByStudentAndCoopPosition = new ArrayList<>();
		 for(RequiredDocument rdoc: requiredDocumentRepository.findRequiredDocumentByCoopPosition(cp)) {
			 requiredDocumentByStudentAndCoopPosition.add(rdoc);
		 }
		 return requiredDocumentByStudentAndCoopPosition;
	 }
	 
	 @Transactional
	 	public List<RequiredDocument> getRequiredDocumentByDueDate(Date dueDate) {
		 List<RequiredDocument> requiredDocumentByDueDate = new ArrayList<>();
		 for(RequiredDocument rdoc: requiredDocumentRepository.findRequiredDocumentByDueDate(dueDate)) {
			 requiredDocumentByDueDate.add(rdoc);
		 }
		 return requiredDocumentByDueDate;
	 }
	 
	 @Transactional
	 	public RequiredDocument getRequiredDocument(int id) {
		 RequiredDocument rdoc = requiredDocumentRepository.findById(id);
	 		return rdoc;
	 }
	 
	 @Transactional
	 public void setAccepted(int id, Boolean answer) {
	    RequiredDocument rd = requiredDocumentRepository.findById(id);
	    rd.setAccepted(answer);
	    	
	  }
	 

	  @Transactional
	    public List<RequiredDocument> getAllRequiredDocuments(){
	        return (List<RequiredDocument>)requiredDocumentRepository.findAll();
	    }
	    
	    // =============================== Private methods ===============================

	    private Boolean CheckNotEmpty(String s) {
	        return s != null && !s.equals("") && s.trim().length() > 0;
	    }
	    
	    private RequiredDocument createRequiredDocument(String name, Date dueDate, CoopPosition cp, RequiredDocumentType type, CooperatorManager sys) {
	        if (!CheckNotEmpty(name))
	            throw new IllegalArgumentException("Cannot add a document with empty name.");

	        RequiredDocument rdoc = null;
	        if (type == RequiredDocumentType.REPORT) {
	            rdoc = new Report();
	        } else if (type == RequiredDocumentType.EMPLOYER_CONTRACT) {
	            rdoc = new EmployerContract();
	        } else if(type == RequiredDocumentType.FORM) {
	        	rdoc = new Form();
	        }
	        	
	        if (rdoc != null) { 
	            rdoc.setName(name);
	            rdoc.setDueDate(dueDate);
	            rdoc.setCoopPosition(cp);
	            rdoc.setCooperatorManager(sys);
	            requiredDocumentRepository.save(rdoc);
	            return rdoc;
	        }
	        throw new IllegalArgumentException("[Internal error] Failed to create a new document.");
	    }


}
