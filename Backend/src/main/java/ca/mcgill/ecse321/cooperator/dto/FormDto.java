package ca.mcgill.ecse321.cooperator.dto;

import java.util.Date;


public class FormDto extends RequiredDocumentDto {

    public FormDto() {

    }

    public FormDto(String sid) {
        super(sid);
    }


    public FormDto(Integer id, String name, Date dueDate) {
        super(id, name, dueDate);
    }

}
