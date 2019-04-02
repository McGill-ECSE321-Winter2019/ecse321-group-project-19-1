package ca.mcgill.ecse321.cooperator.services;

import ca.mcgill.ecse321.cooperator.Utilities;
import ca.mcgill.ecse321.cooperator.dao.CoopPositionRepository;
import ca.mcgill.ecse321.cooperator.dao.UserEntityRepository;
import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.ProgramManager;
import ca.mcgill.ecse321.cooperator.model.TermInstructor;
import ca.mcgill.ecse321.cooperator.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserEntityService {

    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    CoopPositionRepository coopPositionRepository;

    private enum UserType {
        TERM_INSTRUCTOR,
        PROGRAM_MANAGER
    }

    @Transactional
    public List<UserEntity> getAllUserEntities() {
        return (List<UserEntity>) userEntityRepository.findAll();
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

    @Transactional
    public TermInstructor createTermInstructor(String firstName, String lastName, String email, String password) {
        return (TermInstructor) createUser(firstName, lastName, email, password, UserType.TERM_INSTRUCTOR);
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

    @Transactional
    public ProgramManager createProgramManager(String firstName, String lastName, String email, String password) {
        return (ProgramManager) createUser(firstName, lastName, email, password, UserType.PROGRAM_MANAGER);
    }

    /**
	 * This method check if the login information was valid
	 * 
	 * @param email    the email
	 * @param password the password
	 * @return program manager, term instructor
	 * @throws NullPointerException
	 */

    @Transactional
    public UserEntity login(String email, String password) {
        UserEntity user = userEntityRepository.findUserEntityByEmail(email);
        if (user != null && user.getPasswordHash().equals(hash(password))) {
            return user;
        }
        throw new NullPointerException("No such user.");
    }

    /**
     * This method returns the user entity with a specific email
     * 
     * @param email the email
     * @return the user entity
     */

    @Transactional
    public UserEntity getUserEntityByEmail(String email) {
        return userEntityRepository.findUserEntityByEmail(email);
    }

    // =============================== Private methods ===============================

    /**
     * This method creates a user entity
     * 
     * @param firstName first name of the user entity
	 * @param lastName  last name of the user entity
	 * @param password  password of user entity
	 * @param email     email of user entity
     * @paran type      type of the user entity
	 * @return the new created user entity
     * @throws IllegalArgumentException
     */

    private UserEntity createUser(String firstName, String lastName, String email, String password, UserType type) {
        if (!Utilities.checkNotEmpty(firstName))
            throw new IllegalArgumentException("Cannot add a user with empty firstName.");

        if (!Utilities.checkNotEmpty(lastName))
            throw new IllegalArgumentException("Cannot add a user with empty lastName.");

        if (!Utilities.checkNotEmpty(email))
            throw new IllegalArgumentException("Cannot add a user with empty email.");

        if (!Utilities.checkNotEmpty(password))
            throw new IllegalArgumentException("Cannot add a user with empty password.");

        UserEntity user = null;
        if (type == UserType.PROGRAM_MANAGER) {
            user = new ProgramManager();
        } else if (type == UserType.TERM_INSTRUCTOR) {
            user = new TermInstructor();

        }
        if (user != null) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPasswordHash(hash(password));
            userEntityRepository.save(user);
            return user;
        }
        throw new IllegalArgumentException("[Internal error] Failed to create a new user.");
    }

    /**
	 * Assign coop to a term instructor
	 * 
	 * @param ti the the instructor
	 * @param newCoopPoisitions the coop positions
	 * @return the modified term instructor
	 * @throws NullPointerException
	 */
    @Transactional
    public UserEntity assignCoopToInstructor(TermInstructor ti, Set<CoopPosition> newCoopPositions) {
        UserEntity t = userEntityRepository.findUserEntityByEmail(ti.getEmail());
        Set<TermInstructor> tis = new HashSet<>();
        tis.add((TermInstructor) t);
        for (CoopPosition cp : newCoopPositions) {
            if (cp != null) {
                cp.setTermInstructor(tis);
                coopPositionRepository.save(cp);
            }
        }
        if (t instanceof TermInstructor) {
            ((TermInstructor) t).getCoopPosition().addAll(newCoopPositions);
            userEntityRepository.save(t);
            return t;
        }
        throw new NullPointerException("No such term instructor.");
    }

	/**
	 * Deleting an user entity
	 * 
	 * @param email    of user
	 * @return boolean true = success
     * @throws NullPointerException
	 */

    @Transactional
    public boolean deleteUserEntity(String email) {
        UserEntity ue = userEntityRepository.findUserEntityByEmail(email);
        if (ue == null) {
            throw new NullPointerException("No such user.");
        }
        userEntityRepository.deleteById(email);
        return true;

    }

    /**
     * Hashes a password
     * 
     * @param stringToHash password to hash
     * @return the hashed password
     */

    private String hash(String stringToHash) {
        try {
            MessageDigest passwordDigest = MessageDigest.getInstance("SHA-256");
            passwordDigest.update(stringToHash.getBytes());
            return new String(passwordDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 not found!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}