package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;

@Entity
public class Form extends RequiredDocument {
    @Override
    public String toString() {
        return "Form(id= "+getDocumentId()+")";
    }
}
