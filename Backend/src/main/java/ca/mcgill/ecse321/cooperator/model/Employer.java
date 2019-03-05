package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employer {
    private Integer employerID;
    private Set<EmployerContract> employerContract = new HashSet<>();

    public void setEmployerID(Integer value) {
        this.employerID = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getEmployerID() {
        return this.employerID;
    }

    @OneToMany(mappedBy = "employer")
    public Set<EmployerContract> getEmployerContract() {
        return this.employerContract;
    }

    public void setEmployerContract(Set<EmployerContract> employerContracts) {
        this.employerContract = employerContracts;
    }

    @Override
    public String toString() {
        return "Employer(id= "+getEmployerID()+")";
    }

}
