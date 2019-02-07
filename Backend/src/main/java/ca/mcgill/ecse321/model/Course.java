package ca.mcgill.ecse321.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Course{
   private CooperatorManager cooperatorManager;
   
   @ManyToOne(optional=false)
   public CooperatorManager getCooperatorManager() {
      return this.cooperatorManager;
   }
   
   public void setCooperatorManager(CooperatorManager cooperatorManager) {
      this.cooperatorManager = cooperatorManager;
   }
   
   private String courseName;

private void setCourseName(String value) {
    this.courseName = value;
}
@Id
private String getCourseName() {
    return this.courseName;
}
   private Set<CoopPosition> coopPosition;
   
   @ManyToMany(mappedBy="course" )
   public Set<CoopPosition> getCoopPosition() {
      return this.coopPosition;
   }
   
   public void setCoopPosition(Set<CoopPosition> coopPositions) {
      this.coopPosition = coopPositions;
   }
   
   }
