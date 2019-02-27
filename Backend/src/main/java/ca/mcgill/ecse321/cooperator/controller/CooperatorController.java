package ca.mcgill.ecse321.cooperator.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.cooperator.dto.*;
import ca.mcgill.ecse321.cooperator.model.*;
import ca.mcgill.ecse321.cooperator.services.CooperatorService;

@CrossOrigin(origins = "*")
@RestController
public class CooperatorController {

	@Autowired
	private CooperatorService service;

	//create system
	@PostMapping(value = { "/createSystem/{name}", "/createSystem/{name}/" })
	public CooperatorManagerDto createSystem(@PathVariable("name") String name) throws IllegalArgumentException {
		CooperatorManager system = service.createSystem(name);
		return convertToDto(system);
	}
	
	//create coop
	@PostMapping(value = { "/createCoop", "/createCoop/" })
	public CoopPositionDto createCoopPostion(@RequestParam(name="startDate")Date startDate, @RequestParam(name="endDate")Date endDate, @RequestParam(name="description")String desc, 
			@RequestParam(name="location")String location,@RequestParam(name="term")String term, @RequestParam(name="student") StudentDto studentDto, @RequestParam(name = "system") CooperatorManagerDto cmDto) throws IllegalArgumentException {
		CooperatorManager cm = service.getSystem(cmDto.getName());
		Student student = convertToDomainObject(studentDto);
		CoopPosition coopPostion = service.createCoopPosition(startDate, endDate, desc, location, term, student, cm);
		return convertToDto(coopPostion);
	}

	//create term instructor
	@PostMapping(value = { "/createTermInstructor/{email}", "/createTermInstructor/{email}/" })
	public TermInstructorDto createTermInstructor(@RequestParam("first") String firstName,
			@RequestParam("last") String lastName, @RequestParam("password") String password,
			@PathVariable("email") String email, @RequestParam(name = "system") CooperatorManagerDto cmDto)
			throws IllegalArgumentException {
		// @formatter:on
		CooperatorManager cm = service.getSystem(cmDto.getName());
		TermInstructor termInstructor = service.createTermInstructor(firstName, lastName, password, email, cm);
		return convertToDto(termInstructor);
	}
	
	//create student
	@PostMapping(value = { "/createStudent", "/createStudent/" })
	public StudentDto createStudent(@RequestParam(name = "system") CooperatorManagerDto cmDto)
			throws IllegalArgumentException {
		// @formatter:on
		CooperatorManager cm = service.getSystem(cmDto.getName());
		Student student = service.createStudent(cm);
		return convertToDto(student);
	}
		
	//create course
	@PostMapping(value = {"/createCourse/{courseName}", "/createCourse/{courseName}/" })
	public CourseDto createCourse(@PathVariable("name") String courseName, @RequestParam(name = "system") CooperatorManagerDto cmDto)
			throws IllegalArgumentException {
		CooperatorManager cm = service.getSystem(cmDto.getName());
		Course course = service.createCourse(courseName,cm);
		return convertToDto(course);
	}	
	
	//create employer
	@PostMapping(value = { "/createEmployer", "/createEmployer/" })
	public EmployerDto createEmployer(@RequestParam(name = "system") CooperatorManagerDto cmDto)
			throws IllegalArgumentException {
			CooperatorManager cm = service.getSystem(cmDto.getName());
			Employer employer = service.createEmployer(cm);
			return convertToDto(employer);
	}
	
	//create report
	@PostMapping(value = { "/createReport", "/createReport/" })
	public ReportDto createReport(@RequestParam("title") String title, @RequestParam(name="due") Date dueDate,@RequestParam(name="coop") CoopPosition cp,  
			@RequestParam(name="type") ReportType type, @RequestParam(name = "system") CooperatorManagerDto cmDto)
			throws IllegalArgumentException {
			
			CooperatorManager cm = service.getSystem(cmDto.getName());
			Report report = service.createReport(title,dueDate,cp,type,cm);
			return convertToDto(report);
	}		
	//create form
	@PostMapping(value = { "/createForm/", "/createForm/" })
	public FormDto createForm(@RequestParam("name") String name,@RequestParam(name="due") Date dueDate, @RequestParam(name="coop") CoopPosition cp, 
			@RequestParam(name = "system") CooperatorManagerDto cmDto)
			throws IllegalArgumentException {
			
			CooperatorManager cm = service.getSystem(cmDto.getName());
			Form form = service.createForm(name,dueDate,cp,cm);
			return convertToDto(form);
	}
	
	//create employer contract
	@PostMapping(value = { "/createEmployerContract/", "/createEmployerContract/" })
	public EmployerContractDto createEmployerContract(@RequestParam(name="name") String name,@RequestParam(name="due") Date dueDate, @RequestParam(name="coop") CoopPosition cp,
			@RequestParam(name="employer")Employer e, @RequestParam(name = "system") CooperatorManagerDto cmDto)
			throws IllegalArgumentException {
			
			CooperatorManager cm = service.getSystem(cmDto.getName());
			EmployerContract ec= service.createEmployerContract(name,dueDate,cp,e,cm);
			return convertToDto(ec);
	}		
	
	//view all systems
		@GetMapping(value = { "/systems", "/systems/" })
		public List<CooperatorManagerDto> getAllSystems() {
			List<CooperatorManagerDto> sysDtos = new ArrayList<>();
			for (CooperatorManager sys : service.getAllSystems()) {
				sysDtos.add(convertToDto(sys));
			}
			return sysDtos;
		}

	//view all coops
	@GetMapping(value = { "/coops", "/coops/" })
	public List<CoopPositionDto> getAllCoop() {
		List<CoopPositionDto> coopDtos = new ArrayList<>();
		for (CoopPosition cp : service.getAllCoopPositions()) {
			coopDtos.add(convertToDto(cp));
		}
		return coopDtos;
	}
	
	//view all documents submitted by student
	@GetMapping(value = { "/requiredDocuments", "/requiredDocuments/" })
	public List<RequiredDocumentDto> getRequiredDocumentsByCoopPosition (@RequestParam(name="coop")CoopPositionDto cpDto){
		CoopPosition cp= convertToDomainObject(cpDto);
		List<RequiredDocument> rdDto = service.getAllRequiredDocumentsByCoopPosition(cp);
		List<RequiredDocumentDto> rdDtos= new ArrayList<>();
		for (RequiredDocument r : rdDto) {
			//TODO
		}
		return rdDtos;
	}
	

	//Assign coop to term instructors
	@PostMapping(value = { "/assignCoop", "/assignCoop/" })
	public void assignCoop(@RequestParam(name = "termInstructor") TermInstructorDto tiDto,
			@RequestParam(name = "Coop") CoopPositionDto cpDto) throws IllegalArgumentException {
		//set for Dto
		tiDto.addCoopPostion(cpDto);
		//set for persistence
		TermInstructor ti = (TermInstructor) service.getUserEntityByEmail(tiDto.getEmail());
		Set<CoopPosition> newCoopPositions = new HashSet<CoopPosition>();
		for(CoopPositionDto coopDto : tiDto.getCoopPosition()) {
			CoopPosition newCP = convertToDomainObject(coopDto);
			if(newCP == null) {
				throw new IllegalArgumentException("There is no such coop position!");
			}
			newCoopPositions.add(newCP);
		}
		ti.setCoopPosition(newCoopPositions);
	}
	
	//grade document
	@PostMapping(value = {"/gradeDocument","/gradeDocument/"})
	public void gradeDocument(@RequestParam(name="documentId")RequiredDocument rd,@RequestParam(name="grade") Boolean accepted) throws IllegalArgumentException{
		rd.setAccepted(accepted);
	}
	
	//viewing graded document
	@GetMapping(value= {"/grade","/grade/"})
	public Boolean viewGrade(@RequestParam(name="document")RequiredDocumentDto rd) throws IllegalArgumentException{
		return rd.getAccepted();
	}
	
	//getting problematic students
	@GetMapping(value= {"/problematicStudents","/problematicStudents/"})
	public List<StudentDto> getProblematicStudents() throws IllegalArgumentException{
		List<Student> students = service.getAllProblematicStudents();
		List<StudentDto> studentsDto = new ArrayList<>();
		for(Student s: students) {
			studentsDto.add(convertToDto(s));
		}
		return studentsDto;
	}
	
	//adjudicate completion of coop
	@GetMapping (value= {"/coops","/coops/"})
	public void adjudicateCoop(@RequestParam(name="coop")CoopPositionDto cp, Status status) throws IllegalArgumentException{
		cp.setStatus(status);
	}
	
	//getting list of ranked courses
	@GetMapping(value= {"/ranking","/ranking/"})
	public List<CourseDto> getCoursesRanking(){
		//TODO 
		List<CourseDto> c= new ArrayList<>();
		return c;
		
	}
	
	
	
	
    // =============================== Private methods ===============================
	
	//====REQUIRED DOCUMENT======
	//Report
	private ReportDto convertToDto(Report r) {
		if (r == null) {
			throw new IllegalArgumentException("There is no such report!");
		}
		CooperatorManagerDto cm = convertToDto(r.getCooperatorManager());
		ReportDto rDto = new ReportDto(r.getReportType(),r.getDocumentId(),r.getName(),r.getDueDate(),cm);
		return rDto;
	}
	//Form
	private FormDto convertToDto(Form f) {
		if (f == null) {
			throw new IllegalArgumentException("There is no such form!");
		}
		CooperatorManagerDto cm = convertToDto(f.getCooperatorManager());
		FormDto fDto = new FormDto(f.getDocumentId(),f.getName(),f.getDueDate(),cm);
		return fDto;
	}
	//Employer Contract
	private EmployerContractDto convertToDto(EmployerContract ec) {
		if (ec == null) {
			throw new IllegalArgumentException("There is no such employer contract!");
		}
		CooperatorManagerDto cm = convertToDto(ec.getCooperatorManager());
		EmployerContractDto ecDto = new EmployerContractDto(ec.getDocumentId(),ec.getName(),ec.getDueDate(),cm);
		return ecDto;
	}
	//==========================
	
	private EmployerDto convertToDto(Employer e) {
		if (e == null) {
			throw new IllegalArgumentException("There is no such employer!");
		}
		CooperatorManagerDto cm = convertToDto(e.getCooperatorManager());
		EmployerDto eDto = new EmployerDto(e.getEmployerID(),cm);
		return eDto;
	}
	
	
	private CourseDto convertToDto(Course c) {
		if (c == null) {
			throw new IllegalArgumentException("There is no such course!");
		}
		CooperatorManagerDto cm = convertToDto(c.getCooperatorManager());
		CourseDto cDto = new CourseDto(c.getCourseId(),c.getCourseName(),cm);
		return cDto;
	}
	private CoopPositionDto convertToDto(CoopPosition cp) {
		if (cp == null) {
			throw new IllegalArgumentException("There is no such coop position!");
		}
		StudentDto sDto= convertToDto(cp.getStudent());
		CooperatorManagerDto cm = convertToDto(cp.getCooperatorManager());
		CoopPositionDto cpDto = new CoopPositionDto(cp.getCoopId(), cp.getDescription(),cp.getStartDate(),cp.getEndDate(),cp.getLocation(),cp.getTerm(), sDto, cm);
		return cpDto;
	}
	
	private StudentDto convertToDto(Student s) {
		if (s == null) {
			throw new IllegalArgumentException("There is no such student!");
		}
		CooperatorManagerDto cm = convertToDto(s.getCooperatorManager());
		return new StudentDto(s.getStudentID(),cm);
	}

	private TermInstructorDto convertToDto(TermInstructor ti) {
		if (ti == null) {
			throw new IllegalArgumentException("There is no such term instructor!");
		}
		CooperatorManagerDto cm = convertToDto(ti.getCooperatorManager());
		return new TermInstructorDto(ti.getFirstName(), ti.getLastName(), ti.getPassword(), ti.getEmail(), cm);
	}

	private CooperatorManagerDto convertToDto(CooperatorManager cm) {
		if (cm == null) {
			throw new IllegalArgumentException("There is no such system!");
		}
		return new CooperatorManagerDto(cm.getSystemName());
	}

	private CoopPosition convertToDomainObject(CoopPositionDto cpDto) {
		if (cpDto == null) {
			throw new IllegalArgumentException("There is no such coop position!");
		}
		return service.getCoopPositionByID(cpDto.getCoopID());
	}
	
	private Student convertToDomainObject(StudentDto sDto) {
		if (sDto == null) {
			throw new IllegalArgumentException("There is no such student position!");
		}
		return service.getStudentById(sDto.getStudentId());
	}

}
