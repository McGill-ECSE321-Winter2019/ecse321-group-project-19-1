package ca.mcgill.ecse321.cooperator.dto;

import java.util.List;

public class EmployerDto {
	private Integer employerID;
	private List<EmployerContractDto> ec;
	
	public EmployerDto() {
		
	}
	
	public EmployerDto(Integer id) {
		this.employerID=id;
	}
	
	public List<EmployerContractDto> getEmployerContract(){
		return ec;
	}
	
	public void setEmployerContract(List<EmployerContractDto> ec) {
		this.ec=ec;
	}
	
	public void addEmployerContract(EmployerContractDto ec) {
		this.ec.add(ec);
	}
}
