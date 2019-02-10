package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class CoopPosition {
    private Integer coopId;
    private Status status;
    private Set<TermInstructor> termInstructor;
    private Student student;
    private Set<RequiredDocument> requiredDocument;
    private String description;
    private String term;
    private Date startDate;
    private Date endDate;
    private String location;
    private Set<Course> course;

    public void setCoopId(Integer value) {
        this.coopId = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCoopId() {
        return this.coopId;
    }

    public void setStatus(Status value) {
        this.status = value;
    }

    public Status getStatus() {
        return this.status;
    }

    @ManyToMany
    public Set<TermInstructor> getTermInstructor() {
        return this.termInstructor;
    }

    public void setTermInstructor(Set<TermInstructor> termInstructors) {
        this.termInstructor = termInstructors;
    }

    @ManyToMany(mappedBy = "coopPosition")
    public Set<Course> getCourse() {
        return this.course;
    }

    public void setCourse(Set<Course> courses) {
        this.course = courses;
    }

    @ManyToOne(optional = false)
    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @OneToMany(mappedBy = "coopPosition", cascade = {CascadeType.ALL})
    public Set<RequiredDocument> getRequiredDocument() {
        return this.requiredDocument;
    }

    public void setRequiredDocument(Set<RequiredDocument> requiredDocuments) {
        this.requiredDocument = requiredDocuments;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }


    public void setStartDate(Date value) {
        this.startDate = value;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setEndDate(Date value) {
        this.endDate = value;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setTerm(String value) {
        this.term = value;
    }

    public String getTerm() {
        return this.term;
    }

    public void setLocation(String value) {
        this.location = value;
    }

    public String getLocation() {
        return this.location;
    }
}
