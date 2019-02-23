package ca.mcgill.ecse321.cooperator.dto;

import java.util.List;

public class CourseDto {
	private Integer courseId;
    private String courseName;
    private List<CoopPositionDto> coopPositions;  
    private CooperatorManagerDto cooperatorManager;
	
	public CourseDto() {
		
	}
	
	public CourseDto(Integer id, String courseName,CooperatorManagerDto sys) {
		this.courseId=id;
		this.courseName=courseName;
		this.cooperatorManager=sys;	
	}
	
	public Integer getCourseId() {
		return courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	//Coop positions
	public List<CoopPositionDto> getCoopPositions(){
		return coopPositions;
	}
	
	public void setCoopPositions(List<CoopPositionDto> cp) {
		this.coopPositions=cp;
	}
	
	public void addCoopPosition(CoopPositionDto cp) {
		this.coopPositions.add(cp);
	}
	
	public CooperatorManagerDto getSystem() {
    	return cooperatorManager;
    }
	

}
