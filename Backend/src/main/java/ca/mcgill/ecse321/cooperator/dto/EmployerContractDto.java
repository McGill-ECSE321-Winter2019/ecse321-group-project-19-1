package ca.mcgill.ecse321.cooperator.dto;

import java.util.Date;


public class EmployerContractDto extends RequiredDocumentDto {
    private Integer employerId;

    public EmployerContractDto() {

    }
    public EmployerContractDto(String sid) {
        super(sid);
        this.employerId=null;
    }

    public EmployerContractDto(Integer id, String name, Date dueDate) {
        super(id, name, dueDate);
    }

    public Integer getEmployer() {
        return employerId;
    }

    public void setEmployer(Integer employer) {
        this.employerId = employer;
    }
}
