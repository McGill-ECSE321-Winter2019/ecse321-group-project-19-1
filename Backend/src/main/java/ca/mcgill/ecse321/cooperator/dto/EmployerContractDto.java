package ca.mcgill.ecse321.cooperator.dto;

import java.util.Date;


public class EmployerContractDto extends RequiredDocumentDto {
    private Integer employerId;
    private String evaluation;

    public EmployerContractDto() {

    }

    public EmployerContractDto(String sid) {
        super(sid);
        this.employerId = null;
    }

    public EmployerContractDto(Integer id, String name, Date dueDate, Boolean submitted, Boolean accepted, Integer coopId, Integer employerId, String evaluation) {
        super(id, name, dueDate, submitted, accepted, coopId);
        this.employerId = employerId;
        this.evaluation = evaluation;
    }

    public Integer getEmployer() {
        return employerId;
    }

    public void setEmployer(Integer employer) {
        this.employerId = employer;
    }

    public String getEvaluation() {
        return this.evaluation;
    }

    public void setEvaluation(String value) {
        this.evaluation = value;
    }
}
