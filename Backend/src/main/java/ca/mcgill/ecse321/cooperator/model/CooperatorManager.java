package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class CooperatorManager {
    private Set<UserEntity> userEntity;
    private Set<CoopPosition> coopPosition;
    private Set<Student> student;
    private Set<RequiredDocument> requiredDocument;
    private Set<Course> course;
    private String systemName;
    private Set<Employer> employer;

    public void setSystemName(String value) {
        this.systemName = value;
    }

    @Id
    public String getSystemName() {
        return this.systemName;
    }

    @OneToMany(cascade = {CascadeType.ALL})
    public Set<UserEntity> getUserEntity() {
        return this.userEntity;
    }

    public void setUserEntity(Set<UserEntity> userEntitys) {
        this.userEntity = userEntitys;
    }

    public void addUserEntity(UserEntity u) {
        this.userEntity.add(u);
    }

    @OneToMany(cascade = {CascadeType.ALL})
    public Set<CoopPosition> getCoopPosition() {
        return this.coopPosition;
    }

    public void setCoopPosition(Set<CoopPosition> coopPositions) {
        this.coopPosition = coopPositions;
    }

    public void addCoopPosition(CoopPosition cp) {
        this.coopPosition.add(cp);
    }

    @OneToMany(cascade = {CascadeType.ALL})
    public Set<Student> getStudent() {
        return this.student;
    }

    public void setStudent(Set<Student> students) {
        this.student = students;
    }

    public void addStudent(Student s) {
        this.student.add(s);
    }

    @OneToMany(cascade = {CascadeType.ALL})
    public Set<RequiredDocument> getRequiredDocument() {
        return this.requiredDocument;
    }

    public void setRequiredDocument(Set<RequiredDocument> requiredDocuments) {
        this.requiredDocument = requiredDocuments;
    }

    public void addRequiredDocument(RequiredDocument r) {
        this.requiredDocument.add(r);
    }

    @OneToMany(cascade = {CascadeType.ALL})
    public Set<Course> getCourse() {
        return this.course;
    }

    public void setCourse(Set<Course> courses) {
        this.course = courses;
    }

    public void addCourse(Course c) {
        this.course.add(c);
    }

    @OneToMany(cascade = {CascadeType.ALL})
    public Set<Employer> getEmployer() {
        return this.employer;
    }

    public void setEmployer(Set<Employer> employers) {
        this.employer = employers;
    }

    public void addEmployer(Employer e) {
        this.employer.add(e);
    }
}
