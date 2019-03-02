package ca.mcgill.ecse321.cooperator.dto;

import ca.mcgill.ecse321.cooperator.model.Student;

import java.util.Date;


public abstract class RequiredDocumentDto {
    private Integer documentId;
    private String name;
    private Date dueDate;
    private Boolean submitted;
    private Boolean accepted;
    private Integer studentId;

    public RequiredDocumentDto() {

    }

    public RequiredDocumentDto(String s) {
        this(Integer.parseInt(s), null, null,null,null,null);
    }

    public RequiredDocumentDto(Integer id, String name, Date due) {
        this(id,name,due,false,false,null);
    }

    public RequiredDocumentDto(Integer id, String name, Date due, Boolean submitted, Boolean accepted,Integer studentId) {
        this.documentId = id;
        this.name = name;
        this.dueDate = due;
        this.submitted=submitted;
        this.accepted=accepted;
        this.studentId=studentId;
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
        this.submitted = answer;
    }

    public Boolean getSubmitted() {
        return submitted;
    }

    public void setAccepted(Boolean answer) {
        this.accepted = answer;

    }

    public Boolean getAccepted() {
        return accepted;
    }

    //Student
    public Integer getStudent() {
        return studentId;
    }

    public void setStudent(Integer student) {
        this.studentId = student;
    }

}
