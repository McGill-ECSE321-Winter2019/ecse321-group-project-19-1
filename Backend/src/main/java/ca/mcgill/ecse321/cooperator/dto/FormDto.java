package ca.mcgill.ecse321.cooperator.dto;

import ca.mcgill.ecse321.cooperator.model.ReportType;

import java.util.Date;


public class FormDto extends RequiredDocumentDto {

    public FormDto() {

    }

    public FormDto(String sid) {
        super(sid);
    }

    public FormDto(Integer id, String name, Date dueDate, Boolean submitted, Boolean accepted, Integer coopId) {
        super(id, name, dueDate, submitted, accepted, coopId);
    }
}
