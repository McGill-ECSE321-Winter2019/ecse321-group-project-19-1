package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.dto.EmployerDto;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.services.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class EmployerController {
    @Autowired
    EmployerService employerService;

    // create employer
    @PostMapping(value = {"/createEmployer", "/createEmployer/"})
    public EmployerDto createEmployer()
            throws IllegalArgumentException {
        Employer employer = employerService.createEmployer();
        return DtoConverters.convertToDto(employer);
    }
}
