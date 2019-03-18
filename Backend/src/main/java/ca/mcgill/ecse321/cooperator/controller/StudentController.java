package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.dto.RequiredDocumentDto;
import ca.mcgill.ecse321.cooperator.dto.StudentDto;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.UserEntity;
import ca.mcgill.ecse321.cooperator.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

	/**
	 * Create a new student in the system
	 *
	 * @param firstName the first name of the new student
	 * @param lastName  the last name of the new student
	 * @return a StudentDto representing the new student
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/createStudent", "/createStudent/" })
	public StudentDto createStudent(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName) throws IllegalArgumentException {
		Student student = studentService.createStudent(firstName, lastName);
		return DtoConverters.convertToDto(student);
	}

	/**
	 * Assign a coop position to a student
	 *
	 * @param studentId the Id of the student to be assigned the position
	 * @param cpId      the Id of the coop to be assigned to the student
	 * @return a StudentDto representing the modified student
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/offerCoopToStudent", "/offerCoopToStudent/" })
	public StudentDto offerCoopPosition(@RequestParam(name = "studentId") int studentId,
			@RequestParam(name = "coopId") int cpId) throws IllegalArgumentException {
		return DtoConverters.convertToDto(studentService.offerCoopPostionToStudent(studentId, cpId));
	}

	/**
	 * Submit a required document to a specific coop position by a student
	 *
	 * @param studentId the Id of the student submitting the document
	 * @param cpId      the Id of the coop to which the document is being submitted
	 * @param docId     the Id of the submitted document
	 * @return RequiredDocumentDto representing the submitted document
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/submitRequiredDoc", "/submitRequiredDoc/" })
	public RequiredDocumentDto submitRequiredDoc(@RequestParam(name = "studentId") int studentId,
			@RequestParam(name = "coopId") int cpId, @RequestParam(name = "docId") int docId)
			throws IllegalArgumentException {
		return DtoConverters.convertToDto(studentService.submitRequiredDocumentToCoop(studentId, cpId, docId));
	}
	
	/**
	 * Deleting a student
	 * @param studentId
	 * @return true = success; false = fail
	 */
	@PostMapping(value = { "/deleteStudent", "/deleteStudent/" })
	public boolean deleteStudent(@RequestParam(name = "studentId") int studentId) {
		try {
			studentService.deleteStudent(studentId);
		} catch (NullPointerException e) {
			return false;
		}
		return true;

	}

	/**
	 * Get all problematic students in the system
	 *
	 * @return a list of StudentDto representing all problematic students in the
	 *         system
	 * @throws IllegalArgumentException
	 */
	@GetMapping(value = { "/problematicStudents", "/problematicStudents/" })
	public List<StudentDto> getProblematicStudents() throws IllegalArgumentException {
		List<Student> students = studentService.getAllProblematicStudents();
		List<StudentDto> studentsDto = new ArrayList<>();
		for (Student s : students) {
			studentsDto.add(DtoConverters.convertToDto(s));
		}
		return studentsDto;
	}
}
