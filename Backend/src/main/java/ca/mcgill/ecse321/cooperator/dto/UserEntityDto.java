package ca.mcgill.ecse321.cooperator.dto;

public abstract class UserEntityDto {
	private String firstName;
    private String lastName;
    private String password;
    private String email;
    
    public UserEntityDto() {
    	
    }
    
    public UserEntityDto(String first, String last, String pass, String e_mail) {
    	this.firstName = first;
    	this.lastName = last;
    	this.password = pass;
    	this.email = e_mail;
    }

    public void setFirstName(String value) {
        this.firstName = value;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

    public String getLastName() {
        return lastName;
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
    
    public String getEmail() {
    	return email;
    }
}
