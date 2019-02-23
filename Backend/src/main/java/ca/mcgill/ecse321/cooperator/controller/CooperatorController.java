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
	@PostMapping(value = { "/system/{name}", "/system/{name}/" })
	public CooperatorManagerDto createSystem(@PathVariable("name") String name) throws IllegalArgumentException {
		CooperatorManager system = service.createSystem(name);
		return convertToDto(system);
	}
	
	//create coop
	@PostMapping(value = { "/createCoop", "/createCoop/" })
	public CoopPositionDto createCoopPostion(@RequestParam(name = "system") CooperatorManagerDto cmDto) throws IllegalArgumentException {
		CooperatorManager cm = service.getSystem(cmDto.getName());
		//TODO: change/add code for student. This is just a test
		Student student = service.createStudent(cm);
		CoopPosition coopPostion = service.createCoopPosition(new Date(0), new Date(0), "test", "location", "fall2020", student, cm);
		return convertToDto(coopPostion);
	}

	//create term instructor
	@PostMapping(value = { "/createTermInstructor/{email}", "/createTermInstructor/{email}/" })
	public TermInstructorDto createPerson(@PathVariable("first") String firstName,
			@PathVariable("last") String lastName, @PathVariable("password") String password,
			@PathVariable("email") String email, @RequestParam(name = "system") CooperatorManagerDto cmDto)
			throws IllegalArgumentException {
		// @formatter:on
		CooperatorManager cm = service.getSystem(cmDto.getName());
		TermInstructor termInstructor = service.createTermInstructor(firstName, lastName, password, email, cm);
		return convertToDto(termInstructor);
	}
	
	//create student
	@PostMapping(value = { "/createStudent/{studentId}", "/createStudent/{studentId}/" })
	public StudentDto createStudent(@PathVariable("id") Integer studentId, @RequestParam(name = "system") CooperatorManagerDto cmDto)
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
		// @formatter:on
		CooperatorManager cm = service.getSystem(cmDto.getName());
		Course course = service.createCourse(courseName,cm);
		return convertToDto(course);
	}	
	
	//create employer
	@PostMapping(value = { "/createEmployer/{employerId}", "/createEmployer/{employerId}/" })
	public EmployerDto createEmployer(@PathVariable("id") Integer employerId, @RequestParam(name = "system") CooperatorManagerDto cmDto)
			throws IllegalArgumentException {
			// @formatter:on
			CooperatorManager cm = service.getSystem(cmDto.getName());
			Employer employer = service.createEmployer(cm);
			return convertToDto(employer);
	}
	
	//create report
	@PostMapping(value = { "/createReport/{reportId}", "/createReport/{reportId}/" })
	public ReportDto createReport(@PathVariable("name") String name, @PathVariable("due") Date dueDate, @PathVariable("coop") CoopPosition cp,  @PathVariable("type") ReportType type, @RequestParam(name = "system") CooperatorManagerDto cmDto)
			throws IllegalArgumentException {
			// @formatter:on
			CooperatorManager cm = service.getSystem(cmDto.getName());
			Report report = service.createReport(name,dueDate,cp,type,cm);
			return convertToDto(report);
	}		
	//create form
	@PostMapping(value = { "/createForm/{formId}", "/createForm/{formId}/" })
	public FormDto createForm(@PathVariable("name") String name, @PathVariable("due") Date dueDate, @PathVariable("coop") CoopPosition cp, @RequestParam(name = "system") CooperatorManagerDto cmDto)
			throws IllegalArgumentException {
			// @formatter:on
			CooperatorManager cm = service.getSystem(cmDto.getName());
			Form form = service.createForm(name,dueDate,cp,cm);
			return convertToDto(form);
	}
	
	//create employer contract
	@PostMapping(value = { "/createEmployerContract/{name}", "/createForm/{name}/" })
	public EmployerContractDto createEmployerContract(@PathVariable("name") String name, @PathVariable("due") Date dueDate, @PathVariable("coop") CoopPosition cp,@PathVariable("coop")Employer e, @RequestParam(name = "system") CooperatorManagerDto cmDto)
			throws IllegalArgumentException {
			// @formatter:on
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

}
