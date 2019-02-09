package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class CooperatorManager {
    private Integer cooperatorID;
    private Set<CoopPosition> coopPosition;
    private Set<User> user;
    private Set<UserRole> userRole;
    private Set<Course> course;
    private Set<Student> problematicStudent;
    private Set<Student> student;

    private void setCooperatorID(Integer value) {
        this.cooperatorID = value;
    }

    @Id
    private Integer getCooperatorID() {
        return this.cooperatorID;
    }

    @OneToMany(mappedBy = "cooperatorManager", cascade = {CascadeType.ALL})
    public Set<User> getUser() {
        return this.user;
    }

    public void setUser(Set<User> users) {
        this.user = users;
    }

    @OneToMany(mappedBy = "cooperatorManager", cascade = {CascadeType.ALL})
    public Set<CoopPosition> getCoopPosition() {
        return this.coopPosition;
    }

    public void setCoopPosition(Set<CoopPosition> coopPositions) {
        this.coopPosition = coopPositions;
    }

    @OneToMany(mappedBy = "cooperatorManager", cascade = {CascadeType.ALL})
    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Set<UserRole> userRoles) {
        this.userRole = userRoles;
    }

    @OneToMany(mappedBy = "cooperatorManager", cascade = {CascadeType.ALL})
    public Set<Course> getCourse() {
        return this.course;
    }

    public void setCourse(Set<Course> courses) {
        this.course = courses;
    }

    @OneToMany(mappedBy = "cooperatorManager1", cascade = {CascadeType.ALL})
    public Set<Student> getProblematicStudent() {
        return this.problematicStudent;
    }

    public void setProblematicStudent(Set<Student> problematicStudents) {
        this.problematicStudent = problematicStudents;
    }

    @OneToMany(mappedBy = "cooperatorManager", cascade = {CascadeType.ALL})
    public Set<Student> getStudent() {
        return this.student;
    }

    public void setStudent(Set<Student> students) {
        this.student = students;
    }

}
