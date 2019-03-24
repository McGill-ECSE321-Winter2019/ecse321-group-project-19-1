package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.dto.CoopPositionDto;
import ca.mcgill.ecse321.cooperator.dto.ProgramManagerDto;
import ca.mcgill.ecse321.cooperator.dto.TermInstructorDto;
import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.ProgramManager;
import ca.mcgill.ecse321.cooperator.model.TermInstructor;
import ca.mcgill.ecse321.cooperator.model.UserEntity;
import ca.mcgill.ecse321.cooperator.services.CoopPositionService;
import ca.mcgill.ecse321.cooperator.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
public class UserEntityController {

	@Autowired
	UserEntityService userEntityService;

	@Autowired
	CoopPositionService coopPositionService;

	/***
	 * This method check if the login information was valid
	 * 
	 * @param userEmail    the email
	 * @param userPassword the password
	 * @return program manager, term instructor or something went wrong.
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/login/{email}/{password}", "/login/{email}/{password}/" })
	public String login(@PathVariable("email") String userEmail, @PathVariable("password") String userPassword)
			throws IllegalArgumentException {
		UserEntity uentity;
		try {
			uentity = userEntityService.login(userEmail, userPassword);
			if (uentity instanceof ProgramManager) {
				return "ProgramManager";
			}
			if (uentity instanceof TermInstructor) {
				return "TermInstructor";
			}
		} catch (Exception e) {
			return e.getMessage();
		}

		return "Something went wrong";
	}

	/**
	 * Assign coop to a term instructor
	 * 
	 * @param tiEmail the email of the instructor
	 * @param cpId    the Id of the coop
	 * @return the modified coopP position
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/assignCoop", "/assignCoop/" })
	public CoopPositionDto assignCoop(@RequestParam(name = "email") String tiEmail,
			@RequestParam(name = "coopId") int cpId) throws IllegalArgumentException {
		TermInstructor ti = (TermInstructor) userEntityService.getUserEntityByEmail(tiEmail);
		if(ti == null)
			throw new IllegalArgumentException("No such term instructor");
		CoopPosition cp = coopPositionService.getById(cpId);
		if(cp == null)
			throw new IllegalArgumentException("No such coop position");
		Set<CoopPosition> cps = new HashSet<>();
		cps.add(cp);
		userEntityService.assignCoopToInstructor(ti, cps);
		return DtoConverters.convertToDto(cp);
	}

	/**
	 * Create a new term instructor in the system
	 * 
	 * @param firstName first name of the term instructor
	 * @param lastName  last name of the term instructor
	 * @param password  password of term instructor
	 * @param email     email of term instructor
	 * @return the new created term instructor
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/createTermInstructor/{email}", "/createTermInstructor/{email}/" })
	public TermInstructorDto createTermInstructor(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("password") String password,
			@PathVariable("email") String email) throws IllegalArgumentException {
		UserEntity user = userEntityService.getUserEntityByEmail(email);
		if(user == null)
			throw new IllegalArgumentException("User does not exist");
		TermInstructor termInstructor = userEntityService.createTermInstructor(firstName, lastName, email, password);
		return DtoConverters.convertToDto(termInstructor);
	}

	/**
	 * Create a new program manager in the system
	 * 
	 * @param firstName first name of the program manager
	 * @param lastName  last name of the program manager
	 * @param password  password of program manager
	 * @param email     email of program manager
	 * @return the new created program manager
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/createProgramManager/{email}", "/createProgramManager/{email}/" })
	public ProgramManagerDto createProgramManager(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("password") String password,
			@PathVariable("email") String email) throws IllegalArgumentException {
		UserEntity user = userEntityService.getUserEntityByEmail(email);
		if(user == null)
			throw new IllegalArgumentException("User does not exist");
		ProgramManager pm = userEntityService.createProgramManager(firstName, lastName, email, password);
		return DtoConverters.convertToDto(pm);
	}

	/**
	 * Deleting an user entity
	 * 
	 * @param email    of user
	 * @param password of user
	 * @return true = success
	 */
	@PostMapping(value = { "/deleteUserEntity", "/deleteUserEntity/" })
	public boolean deleteUserEntity(@RequestParam("email") String email, @RequestParam("password") String password) {
		userEntityService.login(email, password);
		userEntityService.deleteUserEntity(email);
		return true;

	}

	/**
	 * View all term instructors in the system
	 * 
	 * @return a list of TermInstructorDto representing all term instructors in the
	 *         system.
	 */
	@GetMapping(value = { "/allTermInstructors", "/allTermInstructors/" })
	public List<TermInstructorDto> getAllTermInstructors() {
		List<TermInstructorDto> instructorsDtos = new ArrayList<>();
		for (UserEntity user : userEntityService.getAllUserEntities()) {
			if (user instanceof TermInstructor)
				instructorsDtos.add(DtoConverters.convertToDto((TermInstructor) user));
		}
		return instructorsDtos;
	}
}
