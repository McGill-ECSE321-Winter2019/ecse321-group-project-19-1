package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class EmployerContract extends RequiredDocument {
    private Employer employer;

    @ManyToOne
    public Employer getEmployer() {
        return this.employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @Override
    public String toString() {
        return "EmployerContract(id= "+getDocumentId()+")";
    }
}
