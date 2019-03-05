package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;

@Entity
public class ProgramManager extends UserEntity {
    @Override
    public String toString() {
        return "ProgramManager(email= "+getEmail()+")";
    }
}
