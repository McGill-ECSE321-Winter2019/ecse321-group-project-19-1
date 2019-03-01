package ca.mcgill.ecse321.cooperator.dto;

import ca.mcgill.ecse321.cooperator.model.ReportType;

import java.util.Date;

public class ReportDto extends RequiredDocumentDto {
    private ReportType reportType;

    public ReportDto() {

    }

    public ReportDto(ReportType rt, Integer id, String name, Date dueDate) {
        super(id, name, dueDate);
        this.reportType = rt;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType rt) {
        this.reportType = rt;
    }
}
