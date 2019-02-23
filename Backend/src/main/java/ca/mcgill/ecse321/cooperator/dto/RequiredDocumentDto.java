package ca.mcgill.ecse321.cooperator.dto;

import java.util.Date;



public abstract class RequiredDocumentDto {
	private Integer documentId;
	private String name;
    private Date dueDate;
    private Boolean submitted;
    private Boolean accepted;
    private StudentDto student;
    private CooperatorManagerDto cooperatorManager;
    
    public RequiredDocumentDto() {
    	
    }
    
    public RequiredDocumentDto(Integer id, String name, Date due, CooperatorManagerDto sys) {
    	this.documentId=id;
    	this.name=name;
    	this.dueDate=due;
    	this.cooperatorManager=sys;	
    }
    

    public Integer getDocumentId() {
    	return documentId;
    }
    public Date getDueDate() {
    	return dueDate;
    }
    
    public String getName() {
    	return name;
    }
    
    //Booleans
    public void setSubmitted(Boolean answer) {
    	this.submitted=answer;
    }
    
    public Boolean getSubmitted() {
    	return submitted;
    }
    
    public void setAccepted(Boolean answer) {
    	this.accepted=answer;
    	
    }
    
    public Boolean getAccepted() {
    	return accepted;
    }
    
    //Student
    public StudentDto getStudent() {
    	return student;
    }
    
    public void setStudent(StudentDto student) {
    	this.student=student;
    }
    
    public CooperatorManagerDto getSystem() {
    	return cooperatorManager;
    }

}
