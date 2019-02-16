package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CooperatorManager {
	private Set<UserEntity> userEntity;
	private Set<CoopPosition> coopPosition;
	private Set<Student> student;
	private Set<RequiredDocument> requiredDocument;
	private Set<Course> course;
	private Integer cooperatorID;
	private Set<Employer> employer;

	public void setCooperatorId(Integer value) {
		this.cooperatorID = value;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getCooperatorId() {
		return this.cooperatorID;
	}

	@OneToMany(mappedBy = "cooperatorManager", cascade = { CascadeType.ALL })
	public Set<UserEntity> getUserEntity() {
		return this.userEntity;
	}

	public void setUserEntity(Set<UserEntity> userEntitys) {
		this.userEntity = userEntitys;
	}

	@OneToMany(mappedBy = "cooperatorManager", cascade = { CascadeType.ALL })
	public Set<CoopPosition> getCoopPosition() {
		return this.coopPosition;
	}

	public void setCoopPosition(Set<CoopPosition> coopPositions) {
		this.coopPosition = coopPositions;
	}	

	@OneToMany(mappedBy = "cooperatorManager", cascade = { CascadeType.ALL })
	public Set<Student> getStudent() {
		return this.student;
	}

	public void setStudent(Set<Student> students) {
		this.student = students;
	}

	@OneToMany(mappedBy = "cooperatorManager", cascade = { CascadeType.ALL })
	public Set<RequiredDocument> getRequiredDocument() {
		return this.requiredDocument;
	}

	public void setRequiredDocument(Set<RequiredDocument> requiredDocuments) {
		this.requiredDocument = requiredDocuments;
	}

	@OneToMany(mappedBy = "cooperatorManager", cascade = { CascadeType.ALL })
	public Set<Course> getCourse() {
		return this.course;
	}

	public void setCourse(Set<Course> courses) {
		this.course = courses;
	}

	@OneToMany(mappedBy = "cooperatorManager", cascade = { CascadeType.ALL })
	public Set<Employer> getEmployer() {
		return this.employer;
	}

	public void setEmployer(Set<Employer> employers) {
		this.employer = employers;
	}

}
