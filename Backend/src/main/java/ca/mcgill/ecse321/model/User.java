package ca.mcgill.ecse321.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class User{
   private String name;

private void setName(String value) {
    this.name = value;
}
private String getName() {
    return this.name;
}
private String email;

private void setEmail(String value) {
    this.email = value;
}
private String getEmail() {
    return this.email;
}
private Integer userID;

private void setUserID(Integer value) {
    this.userID = value;
}
@Id
private Integer getUserID() {
    return this.userID;
}
private String password;

private void setPassword(String value) {
    this.password = value;
}
private String getPassword() {
    return this.password;
}
   private CooperatorManager cooperatorManager;
   
   @ManyToOne(optional=false)
   public CooperatorManager getCooperatorManager() {
      return this.cooperatorManager;
   }
   
   public void setCooperatorManager(CooperatorManager cooperatorManager) {
      this.cooperatorManager = cooperatorManager;
   }
   
   private Set<UserRole> userRole;
   
   @OneToMany(mappedBy="user" )
   public Set<UserRole> getUserRole() {
      return this.userRole;
   }
   
   public void setUserRole(Set<UserRole> userRoles) {
      this.userRole = userRoles;
   }
   
   }
