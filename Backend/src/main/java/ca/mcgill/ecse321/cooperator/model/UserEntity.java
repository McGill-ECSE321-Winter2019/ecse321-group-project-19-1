package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class UserEntity {
    private String firstName;
    private String lastName;
    private String passwordHash;
    private String email;

    public void setFirstName(String value) {
        this.firstName = value;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setPasswordHash(String value) {
        this.passwordHash = value;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    @Id
    public String getEmail() {
        return this.email;
    }
}
