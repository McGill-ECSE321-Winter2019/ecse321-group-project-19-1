package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public abstract class UserRole {
    private User user;
    private Integer roleID;
    private CooperatorManager cooperatorManager;

    @ManyToOne(optional = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRoleID(Integer value) {
        this.roleID = value;
    }

    @Id
    public Integer getRoleID() {
        return this.roleID;
    }

    @ManyToOne(optional = false)
    public CooperatorManager getCooperatorManager() {
        return this.cooperatorManager;
    }

    public void setCooperatorManager(CooperatorManager cooperatorManager) {
        this.cooperatorManager = cooperatorManager;
    }

}
