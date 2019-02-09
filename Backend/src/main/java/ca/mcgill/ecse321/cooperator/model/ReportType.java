package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Embeddable;

@Embeddable
public enum ReportType {
    TWO_WEEKS,
    TERM_SPECIFIC,
    EVALUATION
}
