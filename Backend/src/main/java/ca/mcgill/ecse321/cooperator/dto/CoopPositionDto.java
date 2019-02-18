package ca.mcgill.ecse321.cooperator.dto;

public class CoopPositionDto {
	private Integer coopId;
	
	public CoopPositionDto() {
		
	}
	
	public CoopPositionDto(Integer num) {
		this.coopId = num;
	}
	
	public Integer getCoopID() {
		return coopId;
	}
}
