package ca.mcgill.ecse321.cooperator.services;

import ca.mcgill.ecse321.cooperator.Utilities;
import ca.mcgill.ecse321.cooperator.dao.UserEntityRepository;
import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.ProgramManager;
import ca.mcgill.ecse321.cooperator.model.TermInstructor;
import ca.mcgill.ecse321.cooperator.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Service
public class UserEntityService {
	
    @Autowired
    UserEntityRepository userEntityRepository;

    private enum UserType {
        TERM_INSTRUCTOR,
        PROGRAM_MANAGER
    }

    @Transactional
    public List<UserEntity> getAllUserEntities() {
        return (List<UserEntity>) userEntityRepository.findAll();
    }

    @Transactional
    public TermInstructor createTermInstructor(String firstName, String lastName, String email, String password) {
        return (TermInstructor) createUser(firstName, lastName, email, password, UserType.TERM_INSTRUCTOR);
    }

    @Transactional
    public ProgramManager createProgramManager(String firstName, String lastName, String email, String password) {
        return (ProgramManager) createUser(firstName, lastName, email, password, UserType.PROGRAM_MANAGER);
    }

    @Transactional
    public UserEntity login(String email, String password) {
        UserEntity user = userEntityRepository.findUserEntityByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new NullPointerException("No such user.");
    }

    @Transactional
    public UserEntity getUserEntityByEmail(String email) {
        return userEntityRepository.findUserEntityByEmail(email);
    }

    // =============================== Private methods ===============================

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
            user.setPassword(password);
            userEntityRepository.save(user);
            return user;
        }
        throw new IllegalArgumentException("[Internal error] Failed to create a new user.");
    }

    public UserEntity assignCoopToInstructor(TermInstructor ti, Set<CoopPosition> newCoopPositions) {
        UserEntity t = userEntityRepository.findUserEntityByEmail(ti.getEmail());
        if (t instanceof TermInstructor) {
            ((TermInstructor) t).setCoopPosition(newCoopPositions);
            userEntityRepository.save(t);
            return t;
        }
        throw new NullPointerException("No such term instructor.");
    }
    
    @Transactional
    public void deleteUserEntity(String email) {
    	UserEntity ue = userEntityRepository.findUserEntityByEmail(email);
    	if(ue == null) {
    		throw new NullPointerException("No such user.");
    	}
    	if(ue instanceof TermInstructor) {
    		Set<CoopPosition> cps = ((TermInstructor) ue).getCoopPosition();
    		if(cps.size() > 0) {
    			for(CoopPosition cp : cps) {
    				//if coop position is on going, then cannot delete term instructor
    				if(cp.getEndDate().after(new Date())) { 
    					throw new IllegalStateException("Cannot delete term instructor with on going coop positions.");
    				}
    			}
    		}
    	}
    	userEntityRepository.deleteById(email);	
    	
    }
}