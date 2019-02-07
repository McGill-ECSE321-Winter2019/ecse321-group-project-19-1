package ca.mcgill.ecse321.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public abstract class RequiredDocuments{
   private CoopPosition coopPosition;
   
   @ManyToOne(optional=false)
   public CoopPosition getCoopPosition() {
      return this.coopPosition;
   }
   
   public void setCoopPosition(CoopPosition coopPosition) {
      this.coopPosition = coopPosition;
   }
   
   private String name;

private void setName(String value) {
    this.name = value;
}
private String getName() {
    return this.name;
}
private Integer documentID;

private void setDocumentID(Integer value) {
    this.documentID = value;
}
@Id
private Integer getDocumentID() {
    return this.documentID;
}
private Date dueDate;

private void setDueDate(Date value) {
    this.dueDate = value;
}
private Date getDueDate() {
    return this.dueDate;
}
private Status status;

private void setStatus(Status value) {
    this.status = value;
}
private Status getStatus() {
    return this.status;
}
}
