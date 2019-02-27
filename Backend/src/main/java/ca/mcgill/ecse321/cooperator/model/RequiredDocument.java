package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public abstract class RequiredDocument {
    private Integer documentId;
    private CoopPosition coopPosition;
    private String name;
    private Date dueDate;
    private Boolean submitted;
    private Boolean accepted;

    public void setDocumentId(Integer value) {
        this.documentId = value;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getDocumentId() {
        return this.documentId;
    }

    @ManyToOne(optional = false)
    public CoopPosition getCoopPosition() {
        return this.coopPosition;
    }

    public void setCoopPosition(CoopPosition coopPosition) {
        this.coopPosition = coopPosition;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public void setDueDate(Date value) {
        this.dueDate = value;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setAccepted(Boolean value) {
        this.accepted = value;
    }

    public Boolean getAccepted() {
        return this.accepted;
    }
    
    public void setSubmitted(Boolean value) {
        this.submitted = value;
    }

    public Boolean getSubmitted() {
        return this.submitted;
    }
    
}
