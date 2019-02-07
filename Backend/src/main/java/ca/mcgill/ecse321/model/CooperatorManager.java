package ca.mcgill.ecse321.model;
import javax.persistence.Id;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class CooperatorManager{
private Integer cooperatorID;

private void setCooperatorID(Integer value) {
this.cooperatorID = value;
}
@Id
private Integer getCooperatorID() {
return this.cooperatorID;
}
private Set<User> user;

@OneToMany(mappedBy="cooperatorManager" , cascade={CascadeType.ALL})
public Set<User> getUser() {
   return this.user;
}

public void setUser(Set<User> users) {
   this.user = users;
}

private Set<CoopPosition> coopPosition;

@OneToMany(mappedBy="cooperatorManager" , cascade={CascadeType.ALL})
public Set<CoopPosition> getCoopPosition() {
   return this.coopPosition;
}

public void setCoopPosition(Set<CoopPosition> coopPositions) {
   this.coopPosition = coopPositions;
}

private Set<UserRole> userRole;

@OneToMany(mappedBy="cooperatorManager" , cascade={CascadeType.ALL})
public Set<UserRole> getUserRole() {
   return this.userRole;
}

public void setUserRole(Set<UserRole> userRoles) {
   this.userRole = userRoles;
}

private Set<Course> course;

@OneToMany(mappedBy="cooperatorManager" , cascade={CascadeType.ALL})
public Set<Course> getCourse() {
   return this.course;
}

public void setCourse(Set<Course> courses) {
   this.course = courses;
}

private Set<Student> problematicStudent;

@OneToMany(mappedBy="cooperatorManager1" , cascade={CascadeType.ALL})
public Set<Student> getProblematicStudent() {
   return this.problematicStudent;
}

public void setProblematicStudent(Set<Student> problematicStudents) {
   this.problematicStudent = problematicStudents;
}

   
   private Set<Student> student;
   
   @OneToMany(mappedBy="cooperatorManager" , cascade={CascadeType.ALL})
   public Set<Student> getStudent() {
      return this.student;
   }
   
   public void setStudent(Set<Student> students) {
      this.student = students;
   }
   
   }
