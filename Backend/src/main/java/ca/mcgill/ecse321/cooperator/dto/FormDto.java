package ca.mcgill.ecse321.cooperator.dto;

import java.util.Date;


public class FormDto extends RequiredDocumentDto {
	
	public FormDto() {
		
	}
	
	public FormDto(Integer id, String name,Date dueDate, CooperatorManagerDto sys) {
		super(id,name,dueDate,sys);
	}

}
