package ca.mcgill.ecse321.cooperator.dto;

import java.util.Collections;
import java.util.List;

public class CooperatorManagerDto {
	private String name;
	private List<UserEntityDto> users;
	private List<CoopPositionDto> coops;
	private List<CourseDto> courses;
	private List<EmployerDto> employers;
	private List<RequiredDocumentDto> requiredDocuments;
	private List<StudentDto> students;
	

	public CooperatorManagerDto() {
	}

	@SuppressWarnings("unchecked")
	public CooperatorManagerDto(String name) {
		this(name, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
	}
	
	public CooperatorManagerDto(String name, List<UserEntityDto> usersList, List<CoopPositionDto> cp,  List<CourseDto> c,
			List<EmployerDto> e, List<RequiredDocumentDto> r, List<StudentDto> s) {
		this.name = name;
		this.users = usersList;
		this.coops = cp;
		this.users = usersList;
		this.courses = c;
		this.employers = e;
		this.requiredDocuments = r;
		this.students = s;
	}

	public String getName() {
		return this.name;
	}
	
	public List<UserEntityDto> getUsers(){
		return this.users;
	}
	
	public List<CoopPositionDto> getCoopPositions(){
		return this.coops;
	}
	
	public List<CourseDto> getCourses(){
		return this.courses;
	}
	public List<EmployerDto> getEmployers(){
		return this.employers;
	}
	public List<RequiredDocumentDto> getRequiredDocuments(){
		return this.requiredDocuments;
	}
	public List<StudentDto> getStudents(){
		return this.students;
	}
	
	public void addUser(UserEntityDto user) {
		this.users.add(user);
	}
}
