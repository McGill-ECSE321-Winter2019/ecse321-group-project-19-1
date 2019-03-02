package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.dto.CoopPositionDto;
import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.Status;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.services.CoopPositionService;
import ca.mcgill.ecse321.cooperator.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CoopPostionController {
    @Autowired
    StudentService studentService;

    @Autowired
    CoopPositionService coopPositionService;

    // create coop
    @PostMapping(value = {"/createCoop", "/createCoop/"})
    public CoopPositionDto createCoopPostion(@RequestParam(name = "startDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date startDate,
                                             @RequestParam(name = "endDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date endDate,
                                             @RequestParam(name = "description") String description,
                                             @RequestParam(name = "location") String location, @RequestParam(name = "term") String term,
                                             @RequestParam(name = "studentId") int studentId) throws IllegalArgumentException {
        Student student = studentService.getStudentById(studentId);
        CoopPosition coopPostion = coopPositionService.createCoopPosition(startDate, endDate, description, location, term, student);
        studentService.offerCoopPostionToStudent(student.getStudentID(),coopPostion.getCoopId());
        return DtoConverters.convertToDto(coopPostion);
    }

    // view all coops
    @GetMapping(value = {"/coops", "/coops/"})
    public List<CoopPositionDto> getAllCoop() {
        List<CoopPositionDto> coopDtos = new ArrayList<>();
        for (CoopPosition cp : coopPositionService.getAllCoopPositions()) {
            coopDtos.add(DtoConverters.convertToDto(cp));
        }
        return coopDtos;
    }

    // adjudicate completion of coop
    @PostMapping(value = {"/setCoopStatus", "/setCoopStatus/"})
    public Boolean adjudicateCoop(@RequestParam(name = "coopId") int cpId, Status status)
            throws IllegalArgumentException {
        CoopPosition cp = coopPositionService.getById(cpId);
        if(cp!=null)
            return false;
        cp.setStatus(status);
        return true;
    }

}
