package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
public abstract class RequiredDocuments {
    private Integer documentID;
    private String name;
    private CoopPosition coopPosition;
    private Status status;
    private Date dueDate;

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

    public void setDocumentID(Integer value) {
        this.documentID = value;
    }

    @Id
    public Integer getDocumentID() {
        return this.documentID;
    }

    public void setDueDate(Date value) {
        this.dueDate = value;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setStatus(Status value) {
        this.status = value;
    }

    public Status getStatus() {
        return this.status;
    }
}
