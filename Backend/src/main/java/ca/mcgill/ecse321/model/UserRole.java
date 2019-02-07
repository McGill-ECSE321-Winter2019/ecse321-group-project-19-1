package ca.mcgill.ecse321.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public abstract class UserRole{
   private User user;
   
   @ManyToOne(optional=false)
   public User getUser() {
      return this.user;
   }
   
   public void setUser(User user) {
      this.user = user;
   }
   
   private Integer roleID;

private void setRoleID(Integer value) {
    this.roleID = value;
}
@Id
private Integer getRoleID() {
    return this.roleID;
}
   private CooperatorManager cooperatorManager;
   
   @ManyToOne(optional=false)
   public CooperatorManager getCooperatorManager() {
      return this.cooperatorManager;
   }
   
   public void setCooperatorManager(CooperatorManager cooperatorManager) {
      this.cooperatorManager = cooperatorManager;
   }
   
   }
