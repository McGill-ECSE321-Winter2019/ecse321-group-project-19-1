package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Course {
    private String courseName;
    private CooperatorManager cooperatorManager;
    private Set<CoopPosition> coopPosition;

    public Course(String courseName){
        this.courseName=courseName;
    }

    @ManyToOne(optional = false)
    public CooperatorManager getCooperatorManager() {
        return this.cooperatorManager;
    }

    public void setCooperatorManager(CooperatorManager cooperatorManager) {
        this.cooperatorManager = cooperatorManager;
    }

    public void setCourseName(String value) {
        this.courseName = value;
    }

    @Id
    @Column(name="course_name", unique = true, nullable = false)
    public String getCourseName() {
        return this.courseName;
    }

    @ManyToMany(mappedBy = "courses")
    public Set<CoopPosition> getCoopPosition() {
        return this.coopPosition;
    }

    public void setCoopPosition(Set<CoopPosition> coopPositions) {
        this.coopPosition = coopPositions;
    }

}
