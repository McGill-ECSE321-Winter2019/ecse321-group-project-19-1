package ca.mcgill.ecse321.cooperator.dto;

import ca.mcgill.ecse321.cooperator.model.Report;
import ca.mcgill.ecse321.cooperator.model.ReportType;
import ca.mcgill.ecse321.cooperator.model.RequiredDocument;
import ca.mcgill.ecse321.cooperator.services.RequiredDocumentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ReportDto extends RequiredDocumentDto {
    private ReportType reportType;

    @Autowired
    private RequiredDocumentService requiredDocumentService;

    public ReportDto() {

    }

    public ReportDto(String sid) {
        super(sid);
        reportType=null;
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
