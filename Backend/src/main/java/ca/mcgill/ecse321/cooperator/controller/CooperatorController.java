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

	private CoopPositionDto convertToDto(CoopPosition cp) {
		if (cp == null) {
			throw new IllegalArgumentException("There is no such coop position!");
		}
		CoopPositionDto cpDto = new CoopPositionDto(cp.getCoopId());
		return cpDto;
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
