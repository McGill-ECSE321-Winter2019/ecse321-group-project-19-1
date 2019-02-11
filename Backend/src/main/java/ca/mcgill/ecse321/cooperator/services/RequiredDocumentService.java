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
	
	 Boolean CheckNotEmptyInt(int i){
	        return i!=0;
	    }
	 
	 @Autowired
	 	CoopPositionRepository coopPositionRepository;
	 @Autowired
	    RequiredDocumentRepository requiredDocumentRepository;
	 
	 @Transactional
	    public Report createReport(int id, String name, Date dueDate, CoopPosition cp, ReportType type) {
	        Report report = (Report) createRequiredDocument(id, name, dueDate,cp, RequiredDocumentType.REPORT);
	        report.setReportType(type);
	        return report;
	    }

	 @Transactional
	    public EmployerContract createEmployerContract(int id, String name, Date dueDate, CoopPosition cp) {
	        return (EmployerContract) createRequiredDocument(id, name, dueDate,cp, RequiredDocumentType.EMPLOYER_CONTRACT);
	    }

	 @Transactional
	 	public Form createForm(int id, String name, Date dueDate, CoopPosition cp) {
	        return (Form) createRequiredDocument(id, name, dueDate,cp, RequiredDocumentType.FORM);
	    }
	 
	 @Transactional
	 	public List<RequiredDocument> getRequiredDocumentByStudentAndCoopPosition(Student s, CoopPosition cp) {
		 List<RequiredDocument> requiredDocumentByStudentAndCoopPosition = new ArrayList<>();
		 for(RequiredDocument rdoc: requiredDocumentRepository.findByStudentAndCoopPosition(s, cp)) {
			 requiredDocumentByStudentAndCoopPosition.add(rdoc);
		 }
		 return requiredDocumentByStudentAndCoopPosition;
	 }
	 
	 @Transactional
	 	public List<RequiredDocument> getRequiredDocumentByDueDate(Date dueDate) {
		 List<RequiredDocument> requiredDocumentByDueDate = new ArrayList<>();
		 for(RequiredDocument rdoc: requiredDocumentRepository.findByDueDate(dueDate)) {
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
	    public List<RequiredDocument> getAllRequiredDocuments(){
	        return (List<RequiredDocument>)requiredDocumentRepository.findAll();
	    }
	    
	    // =============================== Private methods ===============================

	    private Boolean CheckNotEmpty(String s) {
	        return s != null && !s.equals("") && s.trim().length() > 0;
	    }
	    
	    private RequiredDocument createRequiredDocument(int id, String name, Date dueDate, CoopPosition cp, RequiredDocumentType type) {
	        if (!CheckNotEmptyInt(id))
	            throw new IllegalArgumentException("Cannot add a document with empty id.");

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
	            rdoc.setDocumentId(id);;
	            rdoc.setName(name);
	            rdoc.setDueDate(dueDate);
	            rdoc.setCoopPosition(cp);
	            requiredDocumentRepository.save(rdoc);
	            return rdoc;
	        }
	        throw new IllegalArgumentException("[Internal error] Failed to create a new document.");
	    }


}
