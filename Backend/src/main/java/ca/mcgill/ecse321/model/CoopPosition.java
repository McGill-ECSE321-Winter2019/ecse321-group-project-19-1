package ca.mcgill.ecse321.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;

@Entity
public class CoopPosition{
   private Integer coopID;

private void setCoopID(Integer value) {
    this.coopID = value;
}
@Id
private Integer getCoopID() {
    return this.coopID;
}
private String description;

private void setDescription(String value) {
    this.description = value;
}
private String getDescription() {
    return this.description;
}
private Date startDate;

private void setStartDate(Date value) {
    this.startDate = value;
}
private Date getStartDate() {
    return this.startDate;
}
private Date endDate;

private void setEndDate(Date value) {
    this.endDate = value;
}
private Date getEndDate() {
    return this.endDate;
}
private String location;

private void setLocation(String value) {
    this.location = value;
}
private String getLocation() {
    return this.location;
}
private String term;

private void setTerm(String value) {
    this.term = value;
}
private String getTerm() {
    return this.term;
}
private Status status;

private void setStatus(Status value) {
    this.status = value;
}
private Status getStatus() {
    return this.status;
}
   private Set<RequiredDocuments> requiredDocuments;
   
   @OneToMany(mappedBy="coopPosition" , cascade={CascadeType.ALL})
   public Set<RequiredDocuments> getRequiredDocuments() {
      return this.requiredDocuments;
   }
   
   public void setRequiredDocuments(Set<RequiredDocuments> requiredDocumentss) {
      this.requiredDocuments = requiredDocumentss;
   }
   
   private Student student;
   
   @ManyToOne(optional=false)
   public Student getStudent() {
      return this.student;
   }
   
   public void setStudent(Student student) {
      this.student = student;
   }
   
   private Set<TermInstructor> termInstructor;
   
   @ManyToMany
   public Set<TermInstructor> getTermInstructor() {
      return this.termInstructor;
   }
   
   public void setTermInstructor(Set<TermInstructor> termInstructors) {
      this.termInstructor = termInstructors;
   }
   
   private CooperatorManager cooperatorManager;
   
   @ManyToOne(optional=false)
   public CooperatorManager getCooperatorManager() {
      return this.cooperatorManager;
   }
   
   public void setCooperatorManager(CooperatorManager cooperatorManager) {
      this.cooperatorManager = cooperatorManager;
   }
   
   private Set<Course> course;
   
   @ManyToMany
   public Set<Course> getCourse() {
      return this.course;
   }
   
   public void setCourse(Set<Course> courses) {
      this.course = courses;
   }
   
   }
