package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.dto.TermInstructorDto;
import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.TermInstructor;
import ca.mcgill.ecse321.cooperator.model.UserEntity;
import ca.mcgill.ecse321.cooperator.services.CoopPositionService;
import ca.mcgill.ecse321.cooperator.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
public class UserEntityController {
    @Autowired
    UserEntityService userEntityService;

    @Autowired
    CoopPositionService coopPositionService;

    // Assign coop to term instructors
    @PostMapping(value = {"/assignCoop", "/assignCoop/"})
    public TermInstructorDto assignCoop(@RequestParam(name = "email") String tiEmail,
                                        @RequestParam(name = "coopId") int cpId) throws IllegalArgumentException {

        TermInstructor ti = (TermInstructor) userEntityService.getUserEntityByEmail(tiEmail);
        CoopPosition cp = coopPositionService.getById(cpId);
        Set<CoopPosition> newCoopPositions = ti.getCoopPosition();
        newCoopPositions.add(cp);
        return DtoConverters.convertToDto((TermInstructor) userEntityService.assignCoopToInstructor(ti, newCoopPositions));
    }

    // create term instructor
    @PostMapping(value = {"/createTermInstructor/{email}", "/createTermInstructor/{email}/"})
    public TermInstructorDto createTermInstructor(@RequestParam("firstName") String firstName,
                                                  @RequestParam("lastName") String lastName,
                                                  @RequestParam("password") String password,
                                                  @PathVariable("email") String email)
            throws IllegalArgumentException {
        TermInstructor termInstructor = userEntityService.createTermInstructor(firstName, lastName, email, password);
        return DtoConverters.convertToDto(termInstructor);
    }

    // view all TermInstructor
    @GetMapping(value = {"/termInstructors", "/termInstructors/"})
    public List<TermInstructorDto> getAllTermInstructors() {
        List<TermInstructorDto> instructorsDtos = new ArrayList<>();
        for (UserEntity user : userEntityService.getAllUserEntities()) {
            if (user instanceof TermInstructor)
                instructorsDtos.add(DtoConverters.convertToDto((TermInstructor) user));
        }
        return instructorsDtos;
    }


}
