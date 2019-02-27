package ca.mcgill.ecse321.cooperator.dto;

import java.util.Date;

import ca.mcgill.ecse321.cooperator.model.ReportType;

public class ReportDto extends RequiredDocumentDto {
	private ReportType reportType;
	
	public ReportDto() {
		
	}
	
	public ReportDto(ReportType rt, Integer id, String name,Date dueDate) {
		super(id,name,dueDate);
		this.reportType=rt;	
	}
	
	public ReportType getReportType() {
		return reportType;
	}
	
	public void setReportType(ReportType rt) {
		this.reportType=rt;
	}
}
