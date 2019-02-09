package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Embeddable;

@Embeddable
public enum Status {
    PENDING,
    ACCEPTED,
    REJECTED,
    COMPLETED,
    FAILED
}
