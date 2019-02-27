package ca.mcgill.ecse321.cooperator.dto;

import java.util.List;

public class StudentDto {
	 private Integer studentID;
	 private Boolean problematic;
	 private List<CoopPositionDto> coopPositions;
	 private List<RequiredDocumentDto> requiredDocuments;
	 
	 
	 public StudentDto() {
		 
	 }
	 
	 public StudentDto(Integer id) {
		 this.studentID=id;
	 }
	 
	 public Integer getStudentId() {
		 return studentID;
	 }
	 
	 //Coop position
	 public List<CoopPositionDto> getCoopPositions(){
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
		 this.problematic=isProblematic;
	 }
	 
	 public Boolean getProblematic() {
		 return problematic;
	 }
	 
	 //Required documents
	 public List<RequiredDocumentDto> getRequiredDocuments(){
		 return requiredDocuments;
	 }
	 
	 public void setRequiredDocuments(List<RequiredDocumentDto> rd) {
		 this.requiredDocuments=rd;
	 }
	 
	 public void addRequiredDocument(RequiredDocumentDto rd) {
		 this.requiredDocuments.add(rd);
	 }
}
