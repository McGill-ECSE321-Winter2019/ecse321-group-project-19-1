package ca.mcgill.ecse321.cooperator.dto;

import java.util.Date;


public abstract class RequiredDocumentDto {
    private Integer documentId;
    private String name;
    private Date dueDate;
    private Boolean submitted;
    private Boolean accepted;
    private Integer coopId;

    public RequiredDocumentDto() {

    }

    public RequiredDocumentDto(String s) {
        this(Integer.parseInt(s), null, null,null,null,null);
    }

    public RequiredDocumentDto(Integer id, String name, Date due) {
        this(id,name,due,false,false,null);
    }

    public RequiredDocumentDto(Integer id, String name, Date due, Boolean submitted, Boolean accepted,Integer coopId) {
        this.documentId = id;
        this.name = name;
        this.dueDate = due;
        this.submitted=submitted;
        this.accepted=accepted;
        this.coopId = coopId;
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
    public Integer getCoopId() {
        return coopId;
    }

    public void setCoopId(Integer student) {
        this.coopId = student;
    }

}
