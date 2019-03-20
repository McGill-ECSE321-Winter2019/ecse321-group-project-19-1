package ca.mcgill.ecse321.cooperator.dto;

import java.util.List;

public class CourseDto {
    private Integer courseId;
    private String courseName;
    private List<Integer> coopPositions;

    public CourseDto() {

    }

    public CourseDto(String courseName) {
        this(null, courseName);
    }

    public CourseDto(Integer id, String courseName) {
        this.courseId = id;
        this.courseName = courseName;
    }
    
    public CourseDto(Integer id, String courseName, List<Integer> coopPositions) {
        this.courseId = id;
        this.courseName = courseName;
        this.coopPositions = coopPositions;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    //Coop positions
    public List<Integer> getCoopPositions() {
        return coopPositions;
    }

    public void setCoopPositions(List<Integer> cp) {
        this.coopPositions = cp;
    }

    public void addCoopPosition(Integer cp) {
        this.coopPositions.add(cp);
    }

}
