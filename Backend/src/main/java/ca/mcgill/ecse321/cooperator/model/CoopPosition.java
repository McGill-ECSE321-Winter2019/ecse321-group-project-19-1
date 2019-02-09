package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class CoopPosition {
    private Integer coopID;
    private String description;
    private Date startDate;
    private Date endDate;
    private String term;
    private String location;
    private Status status;
    private Set<Course> courses;
    private Student student;
    private Set<TermInstructor> termInstructor;
    private CooperatorManager cooperatorManager;
    private Set<RequiredDocuments> requiredDocuments;

    private void setCoopID(Integer value) {
        this.coopID = value;
    }

    @Id
    @Column(name="coop_id", unique = true, nullable = false)
    private Integer getCoopID() {
        return this.coopID;
    }

    private void setDescription(String value) {
        this.description = value;
    }

    private String getDescription() {
        return this.description;
    }

    private void setStartDate(Date value) {
        this.startDate = value;
    }

    private Date getStartDate() {
        return this.startDate;
    }

    private void setEndDate(Date value) {
        this.endDate = value;
    }

    private Date getEndDate() {
        return this.endDate;
    }

    private void setLocation(String value) {
        this.location = value;
    }

    private String getLocation() {
        return this.location;
    }

    private void setTerm(String value) {
        this.term = value;
    }

    private String getTerm() {
        return this.term;
    }

    private void setStatus(Status value) {

        this.status = value;
    }

    private Status getStatus() {

        return this.status;
    }

    @OneToMany(mappedBy = "coopPosition", cascade = {CascadeType.ALL})
    public Set<RequiredDocuments> getRequiredDocuments() {
        return this.requiredDocuments;
    }

    public void setRequiredDocuments(Set<RequiredDocuments> requiredDocumentss) {
        this.requiredDocuments = requiredDocumentss;
    }

    @ManyToOne(optional = false)
    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "CoopPosition_TermInstructor",
            joinColumns = { @JoinColumn(name = "coop_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    public Set<TermInstructor> getTermInstructor() {
        return this.termInstructor;
    }

    public void setTermInstructor(Set<TermInstructor> termInstructors) {
        this.termInstructor = termInstructors;
    }

    @ManyToOne(optional = false)
    public CooperatorManager getCooperatorManager() {
        return this.cooperatorManager;
    }

    public void setCooperatorManager(CooperatorManager cooperatorManager) {
        this.cooperatorManager = cooperatorManager;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "CoopPosition_Course",
            joinColumns = { @JoinColumn(name = "coop_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_name") }
    )
    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

}
