package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;

@Entity
public class Report extends RequiredDocument {
    private ReportType reportType;

    public void setReportType(ReportType value) {
        this.reportType = value;
    }

    public ReportType getReportType() {
        return this.reportType;
    }
}
