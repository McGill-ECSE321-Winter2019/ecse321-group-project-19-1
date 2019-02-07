package ca.mcgill.ecse321.model;

import javax.persistence.Entity;

@Entity
public class Report extends RequiredDocuments{
   private ReportType reportType;

private void setReportType(ReportType value) {
    this.reportType = value;
}
private ReportType getReportType() {
    return this.reportType;
}
}
