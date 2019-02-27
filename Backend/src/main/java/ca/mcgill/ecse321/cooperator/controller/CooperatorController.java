package ca.mcgill.ecse321.cooperator.controller;

import java.util.*;

import org.aspectj.apache.bcel.generic.SwitchBuilder;
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

	// ================== POST =================

	// create coop
	@PostMapping(value = { "/createCoop", "/createCoop/" })
	public CoopPositionDto createCoopPostion(@RequestParam(name = "startDate") Date startDate,
			@RequestParam(name = "endDate") Date endDate, @RequestParam(name = "description") String desc,
			@RequestParam(name = "location") String location, @RequestParam(name = "term") String term,
			@RequestParam(name = "student") StudentDto studentDto) throws IllegalArgumentException {
		Student student = convertToDomainObject(studentDto);
		CoopPosition coopPostion = service.createCoopPosition(startDate, endDate, desc, location, term, student);
		return convertToDto(coopPostion);
	}

	// create term instructor
	@PostMapping(value = { "/createTermInstructor/{email}", "/createTermInstructor/{email}/" })
	public TermInstructorDto createTermInstructor(@RequestParam("first") String firstName,
			@RequestParam("last") String lastName, @RequestParam("password") String password,
			@PathVariable("email") String email)
			throws IllegalArgumentException {
		TermInstructor termInstructor = service.createTermInstructor(firstName, lastName, password, email);
		return convertToDto(termInstructor);
	}

	// create student
	@PostMapping(value = { "/createStudent", "/createStudent/" })
	public StudentDto createStudent()
			throws IllegalArgumentException {
		Student student = service.createStudent();
		return convertToDto(student);
	}

	// create course
	@PostMapping(value = { "/createCourse/{name}", "/createCourse/{name}/" })
	public CourseDto createCourse(@PathVariable("name") String courseName) throws IllegalArgumentException {
		Course course = service.createCourse(courseName);
		return convertToDto(course);
	}

	// create employer
	@PostMapping(value = { "/createEmployer", "/createEmployer/" })
	public EmployerDto createEmployer()
			throws IllegalArgumentException {
		Employer employer = service.createEmployer();
		return convertToDto(employer);
	}

	// create report
	@PostMapping(value = { "/createReport", "/createReport/" })
	public ReportDto createReport(@RequestParam("title") String title, @RequestParam(name = "due") Date dueDate,
			@RequestParam(name = "coop") CoopPosition cp, @RequestParam(name = "type") ReportType type) throws IllegalArgumentException {
		Report report = service.createReport(title, dueDate, cp, type);
		return convertToDto(report);
	}

	// create form
	@PostMapping(value = { "/createForm/", "/createForm/" })
	public FormDto createForm(@RequestParam("name") String name, @RequestParam(name = "due") Date dueDate,
			@RequestParam(name = "coop") CoopPosition cp)
			throws IllegalArgumentException {
		Form form = service.createForm(name, dueDate, cp);
		return convertToDto(form);
	}

	// create employer contract
	@PostMapping(value = { "/createEmployerContract/", "/createEmployerContract/" })
	public EmployerContractDto createEmployerContract(@RequestParam(name = "name") String name,
			@RequestParam(name = "due") Date dueDate, @RequestParam(name = "coop") CoopPosition cp,
			@RequestParam(name = "employer") Employer e)
			throws IllegalArgumentException {
		EmployerContract ec = service.createEmployerContract(name, dueDate, cp, e);
		return convertToDto(ec);
	}

	// Assign coop to term instructors
	@PostMapping(value = { "/assignCoop", "/assignCoop/" })
	public void assignCoop(@RequestParam(name = "termInstructor") TermInstructorDto tiDto,
			@RequestParam(name = "Coop") CoopPositionDto cpDto) throws IllegalArgumentException {
		// set for Dto
		tiDto.addCoopPostion(cpDto);
		// set for persistence
		TermInstructor ti = (TermInstructor) service.getUserEntityByEmail(tiDto.getEmail());
		Set<CoopPosition> newCoopPositions = new HashSet<CoopPosition>();
		for (CoopPositionDto coopDto : tiDto.getCoopPosition()) {
			CoopPosition newCP = convertToDomainObject(coopDto);
			if (newCP == null) {
				throw new IllegalArgumentException("There is no such coop position!");
			}
			newCoopPositions.add(newCP);
		}
		ti.setCoopPosition(newCoopPositions);
	}

	// grade document
	@PostMapping(value = { "/gradeDocument", "/gradeDocument/" })
	public void gradeDocument(@RequestParam(name = "documentId") RequiredDocument rd,
			@RequestParam(name = "grade") Boolean accepted) throws IllegalArgumentException {
		rd.setAccepted(accepted);
	}
	// ==============================

	// ================== GET ===============

	// view all coops
	@GetMapping(value = { "/coops", "/coops/" })
	public List<CoopPositionDto> getAllCoop() {
		List<CoopPositionDto> coopDtos = new ArrayList<>();
		for (CoopPosition cp : service.getAllCoopPositions()) {
			coopDtos.add(convertToDto(cp));
		}
		return coopDtos;
	}
	
	// view all courses
		@GetMapping(value = { "/courses", "/courses/" })
		public List<CourseDto> getAllCourses() {
			List<CourseDto> coursesDto = new ArrayList<>();
			for (Course course : service.getAllCourses()) {
				coursesDto.add(convertToDto(course));
			}
			return coursesDto;
		}

	// view all documents submitted by student for specific coop
	@GetMapping(value = { "/requiredDocuments", "/requiredDocuments/" })
	public List<RequiredDocumentDto> getRequiredDocumentsByCoopPosition(
			@RequestParam(name = "coop") CoopPositionDto cpDto) {
		CoopPosition cp = convertToDomainObject(cpDto);
		List<RequiredDocument> rdDto = service.getAllRequiredDocumentsByCoopPosition(cp);
		List<RequiredDocumentDto> rdDtos = new ArrayList<>();
		for (RequiredDocument r : rdDto) {
			RequiredDocumentDto rDto = convertToDto(r);
			rdDtos.add(rDto);
		}
		return rdDtos;
	}

	// viewing graded document
	@GetMapping(value = { "/grade", "/grade/" })
	public Boolean viewGrade(@RequestParam(name = "document") RequiredDocumentDto rd) throws IllegalArgumentException {
		return rd.getAccepted();
	}

	// getting problematic students
	@GetMapping(value = { "/problematicStudents", "/problematicStudents/" })
	public List<StudentDto> getProblematicStudents() throws IllegalArgumentException {
		List<Student> students = service.getAllProblematicStudents();
		List<StudentDto> studentsDto = new ArrayList<>();
		for (Student s : students) {
			studentsDto.add(convertToDto(s));
		}
		return studentsDto;
	}

	// adjudicate completion of coop
	@GetMapping(value = { "/setCoopStatus", "/setCoopStatus/" })
	public void adjudicateCoop(@RequestParam(name = "coop") CoopPositionDto cp, Status status)
			throws IllegalArgumentException {
		cp.setStatus(status);
	}

	// getting list of ranked courses
	@GetMapping(value = { "/ranking", "/ranking/" })
	public List<CourseDto> getCoursesRanking() {
		List<Course> courses = service.getAllCourses();
		Map<CourseDto, Integer> courseDtoMap = new HashMap<CourseDto, Integer>();
		for (Course c : courses) {
			CourseDto cDto = convertToDto(c);
			courseDtoMap.put(cDto, c.getCoopPosition().size());
		}
		Map<CourseDto, Integer> sortedDtoMap = sortByValue(courseDtoMap);
		List<CourseDto> courseList = new ArrayList<CourseDto>(sortedDtoMap.keySet());
		return courseList;
	}
	// =================

	// ================= Private methods =================
	/***
	 * This method is used to sort the courses by the number of coop associated by
	 * them
	 * 
	 * @param map
	 * @return sorted map
	 */
	private static HashMap<CourseDto, Integer> sortByValue(Map<CourseDto, Integer> m) {
		// Create a list from elements of HashMap
		List<Map.Entry<CourseDto, Integer>> list = new LinkedList<Map.Entry<CourseDto, Integer>>(m.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<CourseDto, Integer>>() {
			public int compare(Map.Entry<CourseDto, Integer> o1, Map.Entry<CourseDto, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<CourseDto, Integer> temp = new LinkedHashMap<CourseDto, Integer>();
		for (Map.Entry<CourseDto, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	private RequiredDocumentDto convertToDto(RequiredDocument r) {
		if (r == null) {
			throw new IllegalArgumentException("There is no such document!");
		}
		if (r instanceof Form) {
			return (RequiredDocumentDto) convertToDto((Form) r);
		} else if (r instanceof Report) {
			return (RequiredDocumentDto) convertToDto((Report) r);
		} else if (r instanceof EmployerContract) {
			return (RequiredDocumentDto) convertToDto((EmployerContract) r);
		}
		throw new IllegalArgumentException("This Document is not a instance of any subclass!");
	}

	private ReportDto convertToDto(Report r) {
		if (r == null) {
			throw new IllegalArgumentException("There is no such report!");
		}
		ReportDto rDto = new ReportDto(r.getReportType(), r.getDocumentId(), r.getName(), r.getDueDate());
		return rDto;
	}

	private FormDto convertToDto(Form f) {
		if (f == null) {
			throw new IllegalArgumentException("There is no such form!");
		}
		FormDto fDto = new FormDto(f.getDocumentId(), f.getName(), f.getDueDate());
		return fDto;
	}

	private EmployerContractDto convertToDto(EmployerContract ec) {
		if (ec == null) {
			throw new IllegalArgumentException("There is no such employer contract!");
		}
		EmployerContractDto ecDto = new EmployerContractDto(ec.getDocumentId(), ec.getName(), ec.getDueDate());
		return ecDto;
	}

	private EmployerDto convertToDto(Employer e) {
		if (e == null) {
			throw new IllegalArgumentException("There is no such employer!");
		}
		EmployerDto eDto = new EmployerDto(e.getEmployerID());
		return eDto;
	}

	private CourseDto convertToDto(Course c) {
		if (c == null) {
			throw new IllegalArgumentException("There is no such course!");
		}
		CourseDto cDto = new CourseDto(c.getCourseId(), c.getCourseName());
		return cDto;
	}

	private CoopPositionDto convertToDto(CoopPosition cp) {
		if (cp == null) {
			throw new IllegalArgumentException("There is no such coop position!");
		}
		StudentDto sDto = convertToDto(cp.getStudent());
		CoopPositionDto cpDto = new CoopPositionDto(cp.getCoopId(), cp.getDescription(), cp.getStartDate(),
				cp.getEndDate(), cp.getLocation(), cp.getTerm(), sDto);
		return cpDto;
	}

	private StudentDto convertToDto(Student s) {
		if (s == null) {
			throw new IllegalArgumentException("There is no such student!");
		}
		return new StudentDto(s.getStudentID());
	}

	private TermInstructorDto convertToDto(TermInstructor ti) {
		if (ti == null) {
			throw new IllegalArgumentException("There is no such term instructor!");
		}
		return new TermInstructorDto(ti.getFirstName(), ti.getLastName(), ti.getPassword(), ti.getEmail());
	}

	private ProgramManagerDto convertToDto(ProgramManager pm) {
		if (pm == null) {
			throw new IllegalArgumentException("Program Manager cannot be empty!");
		}
		return new ProgramManagerDto(pm.getFirstName(), pm.getLastName(), pm.getPassword(), pm.getEmail());
	}

	private UserEntityDto convertToDto(UserEntity ue) {
		if (ue == null) {
			throw new IllegalArgumentException("There is no such user!");
		}
		if (ue instanceof TermInstructor) {
			return (UserEntityDto) convertToDto((TermInstructor) ue);
		} else if (ue instanceof ProgramManager) {
			return (UserEntityDto) convertToDto((ProgramManager) ue);
		}
		throw new IllegalArgumentException("User not term instructor or program manager!");
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

	// ==========================
}
