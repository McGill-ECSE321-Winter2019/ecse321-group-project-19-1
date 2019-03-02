package ca.mcgill.ecse321.cooperator.dto;

import java.util.Collections;

public class ProgramManagerDto extends UserEntityDto {
    public ProgramManagerDto() {

    }

    public ProgramManagerDto(String email) {
        this(null, null, null, email);
    }

    public ProgramManagerDto(String firstName, String lastName, String password, String email) {
        super(firstName, lastName, password, email);
    }

}
