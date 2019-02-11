package ca.mcgill.ecse321.cooperator.services;

import ca.mcgill.ecse321.cooperator.dao.UserEntityRepository;
import ca.mcgill.ecse321.cooperator.model.Course;
import ca.mcgill.ecse321.cooperator.model.ProgramManager;
import ca.mcgill.ecse321.cooperator.model.TermInstructor;
import ca.mcgill.ecse321.cooperator.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserEntityService {
    @Autowired
    UserEntityRepository userEntityRepository;

    private enum UserType {
        TERM_INSTRUCTOR,
        PROGRAM_MANAGER
    }

    @Transactional
    public List<UserEntity> getAllUserEntities(){
        return (List<UserEntity>)userEntityRepository.findAll();
    }

    @Transactional
    public TermInstructor createTermInstructor(String firstName, String lastName, String email, String password) {
        return (TermInstructor) createUser(firstName, lastName, email, password, UserType.TERM_INSTRUCTOR);
    }

    @Transactional
    public ProgramManager createProgramManager(String firstName, String lastName, String email, String password) {
        return (ProgramManager) createUser(firstName, lastName, email, password, UserType.PROGRAM_MANAGER);
    }

    public UserEntity getUserEntityByEmail(String email) {
        return userEntityRepository.findUserEntityByEmail(email);
    }

    // =============================== Private methods ===============================

    private Boolean CheckNotEmpty(String s) {
        return s != null && !s.equals("") && s.trim().length() > 0;
    }

    private UserEntity createUser(String firstName, String lastName, String email, String password, UserType type) {
        if (!CheckNotEmpty(firstName))
            throw new IllegalArgumentException("Cannot add a user with empty firstName.");

        if (!CheckNotEmpty(lastName))
            throw new IllegalArgumentException("Cannot add a user with empty lastName.");

        if (!CheckNotEmpty(email))
            throw new IllegalArgumentException("Cannot add a user with empty email.");

        if (!CheckNotEmpty(password))
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
}