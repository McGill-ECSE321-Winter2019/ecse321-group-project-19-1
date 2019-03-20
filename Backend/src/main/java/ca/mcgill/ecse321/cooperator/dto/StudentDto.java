package ca.mcgill.ecse321.cooperator.dto;

import java.util.Collections;
import java.util.List;

public class StudentDto {
    private Integer studentID;
    private Boolean problematic;
    private List<CoopPositionDto> coopPositions;
    private String firstName;
    private String lastName;


    public StudentDto() {

    }

    @SuppressWarnings("unchecked")
    public StudentDto(String sid) {
        this(Integer.parseInt(sid), null, null, null, Collections.EMPTY_LIST);
    }

    public StudentDto(Integer id, String firstName, String lastName, Boolean problematic, List<CoopPositionDto> coopPositions) {
        this(id);
        this.problematic = problematic;
        this.coopPositions = coopPositions;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public StudentDto(Integer id) {
        this.studentID = id;
    }

    public Integer getStudentId() {
        return studentID;
    }

    //Coop position
    public List<CoopPositionDto> getCoopPositions() {
        return coopPositions;
    }

    public void setCoopPosition(List<CoopPositionDto> cps) {
        this.coopPositions = cps;
    }

    public void addCoopPosition(CoopPositionDto cp) {
        this.coopPositions.add(cp);
    }

    //Problematic
    public void setProblematic(Boolean isProblematic) {
        this.problematic = isProblematic;
    }

    public Boolean getProblematic() {
        return problematic;
    }

    public void setFirstName(String value) {
        this.firstName = value;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

    public String getLastName() {
        return this.lastName;
    }
}
