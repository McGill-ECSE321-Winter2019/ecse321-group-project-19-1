package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class UserEntity {
    private Integer useId;
    private String firstName;
    private String lastName;
    private String password;
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

    public void setPassword(String value) {
        this.password = value;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    @Id
    public String getEmail() {
        return this.email;
    }
}
