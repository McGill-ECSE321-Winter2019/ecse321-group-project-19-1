package ca.mcgill.ecse321.cooperator.dto;


public class ProgramManagerDto extends UserEntityDto {
	
	public ProgramManagerDto() {
		
	}
	
	public ProgramManagerDto(String first, String last, String pass, String e_mail, CooperatorManagerDto sys) {
		super(first, last, pass, e_mail, sys);
	}
}
