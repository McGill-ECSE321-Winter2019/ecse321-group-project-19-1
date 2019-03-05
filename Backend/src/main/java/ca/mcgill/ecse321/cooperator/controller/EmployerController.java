package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.dto.EmployerContractDto;
import ca.mcgill.ecse321.cooperator.dto.EmployerDto;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.EmployerContract;
import ca.mcgill.ecse321.cooperator.model.RequiredDocument;
import ca.mcgill.ecse321.cooperator.services.EmployerService;
import ca.mcgill.ecse321.cooperator.services.RequiredDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class EmployerController {
    @Autowired
    EmployerService employerService;

    @Autowired
    RequiredDocumentService requiredDocumentService;

    /**
     * Create a new Employer in the system
     *
     * @return an EmployerDto representing the newly added Employer
     * @throws IllegalArgumentException
     */
    @PostMapping(value = {"/createEmployer", "/createEmployer/"})
    public EmployerDto createEmployer()
            throws IllegalArgumentException {
        Employer employer = employerService.createEmployer();
        return DtoConverters.convertToDto(employer);
    }

    /**
     * Set the evaluation of an employer contract by an employer.
     * @param eId the Id of the employer whos evaluating the contract
     * @param ecId the document to be evaluated
     * @param evaluation the evaluation string
     * @return return an EmployerContractDto representing the evaluated EmployerContract
     * @throws IllegalArgumentException
     */
    @PostMapping(value = {"/setEmployerContractEvaluation", "/setEmployerContractEvaluation/"})
    public EmployerContractDto setEmployerContractEvaluation(@RequestParam(name = "employerId") int eId,
                                                             @RequestParam(name = "employerContractId") int ecId,
                                                             @RequestParam(name = "evaluation") String evaluation) throws IllegalArgumentException {
        RequiredDocument rdoc = requiredDocumentService.getRequiredDocumentById(ecId);
        if (rdoc == null || !(rdoc instanceof EmployerContract))
            return null;
        EmployerContract ec = (EmployerContract) rdoc;
        Employer e = employerService.getEmployerById(ecId);
        return DtoConverters.convertToDto(requiredDocumentService.setEvaluation(ec, e, evaluation));
    }
}
