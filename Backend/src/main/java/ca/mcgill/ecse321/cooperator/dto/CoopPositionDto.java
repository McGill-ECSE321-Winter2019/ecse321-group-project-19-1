package ca.mcgill.ecse321.cooperator.dto;

import java.util.Date;
import java.util.List;

import ca.mcgill.ecse321.cooperator.model.Status;

public class CoopPositionDto {
	private Integer coopId;
	private Status status;
	private String description;
	private String term;
	private Date startDate;
	private Date endDate;
	private String location;

	private StudentDto student;
	private TermInstructorDto termInstructor;
	private List<CourseDto> courses;

	public CoopPositionDto() {

	}
	public CoopPositionDto(String sid) {
		this(sid, null, null, null, null, null, null);
	}

	// Constructor without term instructor with string
	public CoopPositionDto(String sid, String desc, Date start, Date end, String location, String term,
			StudentDto student) {
		Integer id = Integer.parseInt(sid);
		this.coopId = id;
		this.description = desc;
		this.startDate = start;
		this.endDate = end;
		this.location = location;
		this.student = student;
	}

	// Constructor without term instructor
	public CoopPositionDto(Integer id, String desc, Date start, Date end, String location, String term,
			StudentDto student) {
		this.coopId = id;
		this.description = desc;
		this.startDate = start;
		this.endDate = end;
		this.location = location;
		this.student = student;
	}

	// Constructor with term instructor
	public CoopPositionDto(Integer id, String desc, Date start, Date end, String location, String term,
			StudentDto student, TermInstructorDto termInst) {
		this.coopId = id;
		this.description = desc;
		this.startDate = start;
		this.endDate = end;
		this.location = location;
		this.student = student;
		this.termInstructor = termInst;
	}

	public Integer getCoopID() {
		return coopId;
	}

	// Student
	public StudentDto getStudent() {
		return student;
	}

	// Term Instructor
	public TermInstructorDto getTermInstructor() {
		return termInstructor;
	}

	public void setTermInstructor(TermInstructorDto termInstructor) {
		this.termInstructor = termInstructor;
	}

	// Status
	public void setStatus(Status s) {
		this.status = s;
	}

	public Status getStatus() {
		return status;
	}
}
