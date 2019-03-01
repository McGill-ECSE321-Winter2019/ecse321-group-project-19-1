package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.dto.*;
import ca.mcgill.ecse321.cooperator.model.*;
import ca.mcgill.ecse321.cooperator.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class CooperatorController {

    @Autowired
    CoopPositionService coopPositionService;

    @Autowired
    RequiredDocumentService requiredDocumentService;

    @Autowired
    StudentService studentService;

    @Autowired
    CoursesService coursesService;

    @Autowired
    UserEntityService userEntityService;

    @Autowired
    EmployerService employerService;

    // ================== POST =================

    // create coop
    @PostMapping(value = {"/createCoop", "/createCoop/"})
    public CoopPositionDto createCoopPostion(@RequestParam(name = "startDate") Date startDate,
                                             @RequestParam(name = "endDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date endDate,
                                             @RequestParam(name = "description") String description,
                                             @RequestParam(name = "location") String location, @RequestParam(name = "term") String term,
                                             @RequestParam(name = "studentID") StudentDto studentDto) throws IllegalArgumentException {
        Student student = DtoConverters.convertToDomainObject(studentDto, studentService);
        CoopPosition coopPostion = coopPositionService.createCoopPosition(startDate, endDate, description, location, term, student);
        return DtoConverters.convertToDto(coopPostion);
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

    // create student
    @PostMapping(value = {"/createStudent", "/createStudent/"})
    public StudentDto createStudent()
            throws IllegalArgumentException {
        Student student = studentService.createStudent();
        return DtoConverters.convertToDto(student);
    }

    // create course
    @PostMapping(value = {"/createCourse/{courseName}", "/createCourse/{courseName}/"})
    public CourseDto createCourse(@PathVariable("courseName") String courseName) throws IllegalArgumentException {
        Course course = coursesService.createCourse(courseName);
        return DtoConverters.convertToDto(course);
    }

    // create employer
    @PostMapping(value = {"/createEmployer", "/createEmployer/"})
    public EmployerDto createEmployer()
            throws IllegalArgumentException {
        Employer employer = employerService.createEmployer();
        return DtoConverters.convertToDto(employer);
    }

    // create report
    @PostMapping(value = {"/createReport", "/createReport/"})
    public ReportDto createReport(@RequestParam("name") String title,
                                  @RequestParam(name = "dueDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date dueDate,
                                  @RequestParam(name = "coopId") CoopPositionDto cpDto,
                                  @RequestParam(name = "reportType") ReportType type) throws IllegalArgumentException {
        CoopPosition cp = DtoConverters.convertToDomainObject(cpDto, coopPositionService);
        Report report = requiredDocumentService.createReport(title, dueDate, cp, type);
        return DtoConverters.convertToDto(report);
    }

    // create form
    @PostMapping(value = {"/createForm", "/createForm/"})
    public FormDto createForm(@RequestParam("name") String name,
                              @RequestParam(name = "dueDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date dueDate,
                              @RequestParam(name = "coopId") CoopPositionDto cpDto)
            throws IllegalArgumentException {
        CoopPosition cp = DtoConverters.convertToDomainObject(cpDto, coopPositionService);
        Form form = requiredDocumentService.createForm(name, dueDate, cp);
        return DtoConverters.convertToDto(form);
    }

    // create employer contract
    @PostMapping(value = {"/createEmployerContract", "/createEmployerContract/"})
    public EmployerContractDto createEmployerContract(@RequestParam(name = "name") String name,
                                                      @RequestParam(name = "dueDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date dueDate,
                                                      @RequestParam(name = "coopId") CoopPositionDto cpDto,
                                                      @RequestParam(name = "employerId") EmployerDto eDto)
            throws IllegalArgumentException {
        Employer e = DtoConverters.convertToDomainObject(eDto, employerService);
        CoopPosition cp = DtoConverters.convertToDomainObject(cpDto, coopPositionService);
        EmployerContract ec = requiredDocumentService.createEmployerContract(name, dueDate, cp, e);
        return DtoConverters.convertToDto(ec);
    }

    // Assign coop to term instructors
    @PostMapping(value = {"/assignCoop", "/assignCoop/"})
    public TermInstructorDto assignCoop(@RequestParam(name = "email") TermInstructorDto tiDto,
                                        @RequestParam(name = "coopId") CoopPositionDto cpDto) throws IllegalArgumentException {

        TermInstructor ti = (TermInstructor) userEntityService.getUserEntityByEmail(tiDto.getEmail());
        CoopPosition newCP = DtoConverters.convertToDomainObject(cpDto, coopPositionService);
        Set<CoopPosition> newCoopPositions = ti.getCoopPosition();
        newCoopPositions.add(newCP);
        return DtoConverters.convertToDto((TermInstructor) userEntityService.assignCoopToInstructor(ti, newCoopPositions));
    }

    // grade document
    @PostMapping(value = {"/gradeDocument", "/gradeDocument/"})
    public void gradeDocument(@RequestParam(name = "documentId") RequiredDocumentDto rdDto,
                              @RequestParam(name = "grade") Boolean accepted) throws IllegalArgumentException {
        RequiredDocument rd = DtoConverters.convertToDomainObject(rdDto, requiredDocumentService);
        rd.setAccepted(Boolean.valueOf(accepted));
    }
    // ==============================

    // ================== GET ===============

    // view all coops
    @GetMapping(value = {"/coops", "/coops/"})
    public List<CoopPositionDto> getAllCoop() {
        List<CoopPositionDto> coopDtos = new ArrayList<>();
        for (CoopPosition cp : coopPositionService.getAllCoopPositions()) {
            coopDtos.add(DtoConverters.convertToDto(cp));
        }
        return coopDtos;
    }

    // view all courses
    @GetMapping(value = {"/courses", "/courses/"})
    public List<CourseDto> getAllCourses() {
        List<CourseDto> coursesDto = new ArrayList<>();
        for (Course course : coursesService.getAllCourses()) {
            coursesDto.add(DtoConverters.convertToDto(course));
        }
        return coursesDto;
    }

    // view all documents submitted by student for specific coop
    @GetMapping(value = {"/requiredDocuments", "/requiredDocuments/"})
    public List<RequiredDocumentDto> getRequiredDocumentsByCoopPosition(
            @RequestParam(name = "coopId") CoopPositionDto cpDto) {
        CoopPosition cp = DtoConverters.convertToDomainObject(cpDto, coopPositionService);
        List<RequiredDocument> rdDto = requiredDocumentService.getRequiredDocumentByCoopPosition(cp);
        List<RequiredDocumentDto> rdDtos = new ArrayList<>();
        for (RequiredDocument r : rdDto) {
            RequiredDocumentDto rDto = DtoConverters.convertToDto(r);
            rdDtos.add(rDto);
        }
        return rdDtos;
    }

    // viewing graded document
    @GetMapping(value = {"/grade", "/grade/"})
    public Boolean viewGrade(@RequestParam(name = "documentId") RequiredDocumentDto rd) throws IllegalArgumentException {
        return rd.getAccepted();
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

    // adjudicate completion of coop
    @GetMapping(value = {"/setCoopStatus", "/setCoopStatus/"})
    public void adjudicateCoop(@RequestParam(name = "coopId") CoopPositionDto cp, Status status)
            throws IllegalArgumentException {
        cp.setStatus(status);
    }

    // getting list of ranked courses
    @GetMapping(value = {"/ranking", "/ranking/"})
    public List<CourseDto> getCoursesRanking() {
        List<Course> courses = coursesService.getAllCourses();
        Map<Integer, CourseDto> courseDtoMap = new TreeMap<>(Collections.reverseOrder());
        for (Course c : courses)
            courseDtoMap.put(c.getCoopPosition().size(), DtoConverters.convertToDto(c));

        return new ArrayList<>(courseDtoMap.values());
    }
}
