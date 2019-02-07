package ca.mcgill.ecse321.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class TermInstructor extends UserRole{
   private Set<CoopPosition> coopPosition;
   
   @ManyToMany(mappedBy="termInstructor" )
   public Set<CoopPosition> getCoopPosition() {
      return this.coopPosition;
   }
   
   public void setCoopPosition(Set<CoopPosition> coopPositions) {
      this.coopPosition = coopPositions;
   }
   
   }
