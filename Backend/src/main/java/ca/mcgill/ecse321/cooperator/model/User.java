package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    private String name;
    private Set<UserRole> userRole;
    private String email;
    private Integer userID;
    private String password;
    private CooperatorManager cooperatorManager;

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getEmail() {
        return this.email;
    }

    public void setUserID(Integer value) {
        this.userID = value;
    }

    @Id
    @Column(name="user_id", unique = true, nullable = false)
    public Integer getUserID() {
        return this.userID;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public String getPassword() {
        return this.password;
    }

    @ManyToOne(optional = false)
    public CooperatorManager getCooperatorManager() {
        return this.cooperatorManager;
    }

    public void setCooperatorManager(CooperatorManager cooperatorManager) {
        this.cooperatorManager = cooperatorManager;
    }

    @OneToMany(mappedBy = "user")
    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Set<UserRole> userRoles) {
        this.userRole = userRoles;
    }

}
