package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class SystemController {
    @Autowired
    private RequiredDocumentRepository rdocRepo;
    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private UserEntityRepository userRepo;
    @Autowired
    private CoopPositionRepository cpRepo;
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private EmployerRepository employerRepo;

    /**
     * Clear the whole database
     * @throws IllegalArgumentException
     */
    @PostMapping(value = {"/clearDB", "/clearDB/"})
    public void clearDB() throws IllegalArgumentException {
        rdocRepo.deleteAll();
        employerRepo.deleteAll();
        courseRepo.deleteAll();
        userRepo.deleteAll();
        cpRepo.deleteAll();
        studentRepo.deleteAll();
    }
}