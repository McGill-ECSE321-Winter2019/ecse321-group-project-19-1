package ca.mcgill.ecse321.cooperator;

import ca.mcgill.ecse321.cooperator.dao.UserEntityRepository;
import ca.mcgill.ecse321.cooperator.model.ProgramManager;
import ca.mcgill.ecse321.cooperator.model.UserEntity;
import ca.mcgill.ecse321.cooperator.services.UserEntityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorUserEntityTest {

    @Mock
    private UserEntityRepository userEntityDao;

    @InjectMocks
    private UserEntityService userEntityService;

    private UserEntity userEntity;
    private UserEntity userEntity2;
    private static final String firstName = "Hello";
    private static final String lastName = "World";
    private static final String email = "bill.gates@gatesfoundation.com";
    private static final String email2 = "IamSteveJobs@gmail.com";
    private static final String firstName2 = "Jeff";
    private static final String lastName2 = "The legend";
    private static final String password = "Not My Password";
    private static final String WRONG_EMAIL = "Wrong@Email.com";

    private List<UserEntity> expectedList = new ArrayList<UserEntity>();


    @Before
    public void setMockOutput() {
        when(userEntityDao.findUserEntityByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(email)) {
                UserEntity user = new ProgramManager();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                return user;
            } else if (invocation.getArgument(0).equals(email2)) {
                UserEntity user = new ProgramManager();
                user.setFirstName(firstName2);
                user.setLastName(lastName2);
                user.setEmail(email2);
                user.setPassword(password);
                return user;
            }
            return null;

        });
    }


    @Before
    public void setUpMocks() {
        userEntity = mock(UserEntity.class);
        userEntity = userEntityService.createProgramManager(firstName, lastName, email, password);
        expectedList.add(userEntity);
        userEntity2 = mock(UserEntity.class);
        userEntity2 = userEntityService.createProgramManager(firstName2, lastName2, email2, password);
        expectedList.add(userEntity2);
    }

    @Test
    public void testUserEntityCreation() {
        assertNotNull(userEntity);
        assertNotNull(userEntity2);
    }

    @Test
    public void testUserEntityQueryFound() {
        assertEquals(email, userEntityService.getUserEntityByEmail(email).getEmail());
        assertEquals(email2, userEntityService.getUserEntityByEmail(email2).getEmail());
    }

    @Test
    public void testUserEntityQueryNotFound() {
        assertNull(userEntityService.getUserEntityByEmail(WRONG_EMAIL));
    }

}