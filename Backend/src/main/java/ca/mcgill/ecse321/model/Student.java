package ca.mcgill.ecse321.model;
import javax.persistence.OneToMany;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

@Entity
public class Student{
private Set<CoopPosition> coopPosition;
   
   @OneToMany(mappedBy="student" , cascade={CascadeType.ALL})
   public Set<CoopPosition> getCoopPosition() {
      return this.coopPosition;
   }
   
   public void setCoopPosition(Set<CoopPosition> coopPositions) {
      this.coopPosition = coopPositions;
   }
   
   private CooperatorManager cooperatorManager1;
   
   @ManyToOne(optional=false)
   public CooperatorManager getCooperatorManager1() {
      return this.cooperatorManager1;
   }
   
   public void setCooperatorManager1(CooperatorManager cooperatorManager1) {
      this.cooperatorManager1 = cooperatorManager1;
   }
   
   private CooperatorManager cooperatorManager;
   
   @ManyToOne(optional=false)
   public CooperatorManager getCooperatorManager() {
      return this.cooperatorManager;
   }
   
   public void setCooperatorManager(CooperatorManager cooperatorManager) {
      this.cooperatorManager = cooperatorManager;
   }
   
   private Integer studentID;

private void setStudentID(Integer value) {
    this.studentID = value;
}
private Integer getStudentID() {
    return this.studentID;
}
private Boolean problematic;

private void setProblematic(Boolean value) {
    this.problematic = value;
}
private Boolean getProblematic() {
    return this.problematic;
}
   
   }
