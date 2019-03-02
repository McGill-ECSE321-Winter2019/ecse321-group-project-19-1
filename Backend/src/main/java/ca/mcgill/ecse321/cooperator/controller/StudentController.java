package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.dto.StudentDto;
import ca.mcgill.ecse321.cooperator.model.Student;
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

    // create student
    @PostMapping(value = {"/createStudent", "/createStudent/"})
    public StudentDto createStudent(@RequestParam(name = "firstName") String firstName,
                                    @RequestParam(name = "lastName") String lastName) throws IllegalArgumentException {
        Student student = studentService.createStudent(firstName,lastName);
        return DtoConverters.convertToDto(student);
    }

    // Offer a coop position to student
    @PostMapping(value = {"/offerCoopToStudent", "/offerCoopToStudent/"})
    public Boolean offerCoopPosition(@RequestParam(name = "studentId") int studentId,
                                     @RequestParam(name = "coopId") int cpId) throws IllegalArgumentException {
        return studentService.offerCoopPostionToStudent(studentId, cpId);
    }

    // getting problematic students
    @GetMapping(value = {"/problematicStudents", "/problematicStudents/"})
    public List<StudentDto> getProblematicStudents() throws IllegalArgumentException {
        List<Student> students = studentService.getAllProblematicStudents();
        List<StudentDto> studentsDto = new ArrayList<>();
        for (Student s : students) {
            studentsDto.add(DtoConverters.convertToDto(s));
        }
        return studentsDto;
    }
}
