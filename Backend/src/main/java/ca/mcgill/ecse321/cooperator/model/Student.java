package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {
    private Integer studentID;
    private Boolean problematic;
    private Set<CoopPosition> coopPosition;
    private String firstName;
    private String lastName;

    public void setStudentID(Integer value) {
        this.studentID = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getStudentID() {
        return this.studentID;
    }

    public void setProblematic(Boolean value) {
        this.problematic = value;
    }

    public Boolean getProblematic() {
        return this.problematic;
    }

    @OneToMany(mappedBy = "student", cascade = {CascadeType.ALL})
    public Set<CoopPosition> getCoopPosition() {
        return this.coopPosition;
    }

    public void setCoopPosition(Set<CoopPosition> coopPositions) {
        this.coopPosition = coopPositions;
    }

    public void setFirstName(String value) {
        this.firstName = value;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

    public String getLastName() {
        return this.lastName;
    }

}
