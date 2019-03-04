package ca.mcgill.ecse321.cooperator.dto;

import ca.mcgill.ecse321.cooperator.model.Status;

import java.util.Date;
import java.util.List;

public class CoopPositionDto {
    private Integer coopId;
    private Status status;
    private String description;
    private String term;
    private Date startDate;
    private Date endDate;
    private String location;

    private Integer studentId;
    private List<TermInstructorDto> termInstructor;
    private List<CourseDto> courses;

    public CoopPositionDto() {

    }

    public CoopPositionDto(String sid) {
        this(sid, null, null, null, null, null, null);
    }

    // Constructor without term instructor with string
    public CoopPositionDto(String sid, String desc, Date start, Date end, String location, String term,
                           Integer studentId) {
        Integer id = Integer.parseInt(sid);
        this.coopId = id;
        this.description = desc;
        this.startDate = start;
        this.endDate = end;
        this.location = location;
        this.studentId = studentId;
        this.term=term;
    }

    // Constructor without term instructor
    public CoopPositionDto(Integer id, String desc, Date start, Date end, String location, String term,
                           Integer studentId) {
        this.coopId = id;
        this.description = desc;
        this.startDate = start;
        this.endDate = end;
        this.location = location;
        this.studentId = studentId;
        this.term=term;
    }

    // Constructor with term instructor
    public CoopPositionDto(Integer id, String desc, Date start, Date end, String location, String term,
                           Integer studentId, List<TermInstructorDto> termInst) {
        this.coopId = id;
        this.description = desc;
        this.startDate = start;
        this.endDate = end;
        this.term=term;
        this.location = location;
        this.studentId = studentId;
        this.termInstructor = termInst;
    }

    public Integer getCoopID() {
        return coopId;
    }

    public Integer getStudent() {
        return studentId;
    }

    public void setStudent(Integer value) {
        this.studentId=value;
    }

    public List<TermInstructorDto> getTermInstructor() {
        return termInstructor;
    }

    public void setTermInstructor(List<TermInstructorDto> termInstructor) {
        this.termInstructor = termInstructor;
    }

    public void setStatus(Status s) {
        this.status = s;
    }

    public Status getStatus() {
        return status;
    }
}
