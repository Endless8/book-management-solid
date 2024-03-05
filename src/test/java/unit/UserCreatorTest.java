package unit;

import org.example.bookmanagement.dao.User;
import org.example.bookmanagement.dao.UserImp;
import org.example.bookmanagement.repository.UserRepository;
import org.example.bookmanagement.users.UserCreator;
import org.example.bookmanagement.users.UserCreatorImp;
import org.example.bookmanagement.users.UserFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.example.bookmanagement.constants.ErrorMessages.USER_INSERT_ERROR;
import static org.example.bookmanagement.constants.InfoMessages.NEW_USER_INSERTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserCreatorTest {

    private static UserCreator userCreator;
    private static UserFactory userFactory;
    private static UserRepository userRepository;

    @BeforeAll
    static void init() {
        userFactory = mock(UserFactory.class);
        userRepository = mock(UserRepository.class);
        userCreator = new UserCreatorImp(userFactory, userRepository);
    }

    @Test
    void createNewUser() {
        String name = "edoardo";
        String email = "edo@email.com";
        User mockedUser = new UserImp(1, name, email);

        when(userFactory.createUser(name, email)).thenReturn(mockedUser);
        doNothing().when(userRepository).save(mockedUser);

        String result = userCreator.insertNewUser(name, email);
        assertEquals(NEW_USER_INSERTED, result);
    }

    @Test
    void errorCreatingNewUser() {
        String name = "edoardo";
        String email = "edo@email.com";
        User mockedUser = new UserImp(1, name, email);

        when(userFactory.createUser(name, email)).thenReturn(mockedUser);
        doThrow(RuntimeException.class).when(userRepository).save(mockedUser);

        String result = userCreator.insertNewUser(name, email);
        assertEquals(USER_INSERT_ERROR, result);
    }
}
