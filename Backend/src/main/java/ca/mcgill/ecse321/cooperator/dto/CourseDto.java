package ca.mcgill.ecse321.cooperator.dto;

import java.util.List;

public class CourseDto {
	private Integer courseId;
    private String courseName;
    private List<CoopPositionDto> coopPositions;  
	
	public CourseDto() {
		
	}
	
	public CourseDto(Integer id, String courseName) {
		this.courseId=id;
		this.courseName=courseName;
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

}
