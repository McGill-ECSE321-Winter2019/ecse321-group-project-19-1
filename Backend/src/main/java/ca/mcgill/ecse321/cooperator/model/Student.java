package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {
    private Integer studentID;
    private Boolean problematic = true;
    private Set<CoopPosition> coopPosition =new HashSet<>();
    private String firstName;
    private String lastName;

    public Student(){

    }

    public Student(String firstName,String lastName){
        this.firstName=firstName;
        this.lastName=lastName;
    }

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

    public void offerCoopPostion(CoopPosition cp) {
       coopPosition.add(cp);
       // If the student was problematic now it's not anymore
       if(problematic){
           problematic=false;
       }
    }

    @Transient
    public Boolean isProblematic() {
        if (coopPosition.size() == 0) {
            problematic = true;
        } else {
            problematic = false;
        }

        return problematic;
    }

    @Transient
    public Boolean isMissingRequiredDocument() {
        for (CoopPosition cp : coopPosition) {
            for (RequiredDocument rd : cp.getRequiredDocument()) {
                if (rd.getSubmitted() == null || rd.getSubmitted() == false) {
                    return true;
                }
            }
        }
        return false;
    }
}
