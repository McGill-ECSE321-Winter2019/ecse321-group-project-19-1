package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.dto.EmployerContractDto;
import ca.mcgill.ecse321.cooperator.dto.FormDto;
import ca.mcgill.ecse321.cooperator.dto.ReportDto;
import ca.mcgill.ecse321.cooperator.dto.RequiredDocumentDto;
import ca.mcgill.ecse321.cooperator.model.*;
import ca.mcgill.ecse321.cooperator.services.CoopPositionService;
import ca.mcgill.ecse321.cooperator.services.EmployerService;
import ca.mcgill.ecse321.cooperator.services.RequiredDocumentService;
import ca.mcgill.ecse321.cooperator.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class RequiredDocumentController {
    @Autowired
    RequiredDocumentService requiredDocumentService;

    @Autowired
    CoopPositionService coopPositionService;

    @Autowired
    EmployerService employerService;

    @Autowired
    UserEntityService userEntityService;

    /**
     * Create a new report in the system
     *
     * @param name    the name of the report
     * @param dueDate the date for which the report have to submitted before
     * @param cpId    the Id of the coop position associated with this report
     * @param type    the type of the report
     * @return a ReportDto representing the newly added Report
     * @throws IllegalArgumentException
     */
    @PostMapping(value = {"/createReport", "/createReport/"})
    public ReportDto createReport(@RequestParam("name") String name,
                                  @RequestParam(name = "dueDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date dueDate,
                                  @RequestParam(name = "coopId") int cpId,
                                  @RequestParam(name = "reportType") ReportType type) throws IllegalArgumentException {
        CoopPosition cp = coopPositionService.getById(cpId);
        Report report = requiredDocumentService.createReport(name, dueDate, cp, type);
        return DtoConverters.convertToDto(report);
    }

    /**
     * Create a new Form in the system
     *
     * @param name    the name of the Form
     * @param dueDate the date for which the Form have to submitted before
     * @param cpId    the Id of the coop position associated with this Form
     * @return a FormDto representing the newly added Form
     * @throws IllegalArgumentException
     */
    @PostMapping(value = {"/createForm", "/createForm/"})
    public FormDto createForm(@RequestParam("name") String name,
                              @RequestParam(name = "dueDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date dueDate,
                              @RequestParam(name = "coopId") int cpId)
            throws IllegalArgumentException {
        CoopPosition cp = coopPositionService.getById(cpId);
        Form form = requiredDocumentService.createForm(name, dueDate, cp);
        return DtoConverters.convertToDto(form);
    }

    /**
     * Create a new EmployerContract in the system
     *
     * @param name    the name of the EmployerContract
     * @param dueDate the date for which the EmployerContract have to submitted before
     * @param cpId    the Id of the coop position associated with this EmployerContract
     * @param eId     the Id of the employer associated with this EmployerContract
     * @return a EmployerContractDto representing the newly added EmployerContract
     * @throws IllegalArgumentException
     */
    @PostMapping(value = {"/createEmployerContract", "/createEmployerContract/"})
    public EmployerContractDto createEmployerContract(@RequestParam(name = "name") String name,
                                                      @RequestParam(name = "dueDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date dueDate,
                                                      @RequestParam(name = "coopId") int cpId,
                                                      @RequestParam(name = "employerId") int eId)
            throws IllegalArgumentException {
        Employer e = employerService.getEmployerById(eId);
        CoopPosition cp = coopPositionService.getById(cpId);
        EmployerContract ec = requiredDocumentService.createEmployerContract(name, dueDate, cp, e);
        return DtoConverters.convertToDto(ec);
    }

    /**
     * Get all the required documents associated to a coop position.
     *
     * @param cpId the coop position we are targeting
     * @return a list of RequiredDocumentDto representing the requested required documents.
     */
    @GetMapping(value = {"/requiredDocuments", "/requiredDocuments/"})
    public List<RequiredDocumentDto> getRequiredDocumentsByCoopPosition(
            @RequestParam(name = "coopId") int cpId) {
        CoopPosition cp = coopPositionService.getById(cpId);
        List<RequiredDocument> rdDto = requiredDocumentService.getRequiredDocumentByCoopPosition(cp);
        List<RequiredDocumentDto> rdDtos = new ArrayList<>();
        for (RequiredDocument r : rdDto) {
            RequiredDocumentDto rDto = DtoConverters.convertToDto(r);
            rdDtos.add(rDto);
        }
        return rdDtos;
    }

    /**
     * Set the grade (accepted or not accepted) of a required document by an instructor
     *
     * @param rdId            the Id of the document with grade being set
     * @param accepted        indicating if the document is accepted or not by the instructor
     * @param instructorEmail the email of the instructor grading the document
     * @return the graded document, or null if any of the instructor or document are not found
     * @throws IllegalArgumentException
     */
    @PostMapping(value = {"/gradeDocument", "/gradeDocument/"})

    public RequiredDocumentDto gradeDocument(@RequestParam(name = "documentId") int rdId,
                                          @RequestParam(name = "grade") Boolean accepted,
                                          @RequestParam(name = "instructorEmail") String instructorEmail) throws IllegalArgumentException {
        UserEntity user = userEntityService.getUserEntityByEmail(instructorEmail);
        if (user == null || !(user instanceof TermInstructor))
            return null;
        return DtoConverters.convertToDto(requiredDocumentService.gradeDocument(rdId, accepted));
    }

    /**
     * View a document in the system
     *
     * @param rdId the Id id of the document to view
     * @return a RequiredDocumentDto representing th document
     * @throws IllegalArgumentException
     */
    @GetMapping(value = {"/requiredDocument", "/requiredDocument/"})
    public RequiredDocumentDto viewRequiredDocument(@RequestParam(name = "documentId") int rdId) throws IllegalArgumentException {
        RequiredDocument rd = requiredDocumentService.getRequiredDocumentById(rdId);
        if (rd == null)
            return null;
        return DtoConverters.convertToDto(rd);
    }
}
