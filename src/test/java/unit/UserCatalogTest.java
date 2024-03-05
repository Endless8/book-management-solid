package unit;

import org.example.bookmanagement.formatter.MessageFormatter;
import org.example.bookmanagement.dao.User;
import org.example.bookmanagement.repository.UserRepository;
import org.example.bookmanagement.users.UserCatalog;
import org.example.bookmanagement.users.UserCatalogImp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.example.bookmanagement.constants.ErrorMessages.NO_USERS_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserCatalogTest {

    private static UserCatalog userCatalog;
    private static UserRepository userRepository;
    private static MessageFormatter messageFormatter;
    private static List<User> mockedUsers;

    @BeforeAll
    static void init() {
        userRepository = mock(UserRepository.class);
        messageFormatter = mock(MessageFormatter.class);
        userCatalog = new UserCatalogImp(userRepository, messageFormatter);
        mockedUsers = Mocks.getMockedUsers();
    }

    @Test
    void viewAllUsers() {
        String expected = """
                1) Name: 'edoardo' Email: 'edo@email.com'}
                2) Name: 'mimmo' Email: 'mim@email.com'}
                """;

        when(userRepository.getAllUsers()).thenReturn(mockedUsers);
        when(messageFormatter.formatList(mockedUsers)).thenReturn(expected);

        String result = userCatalog.viewAllUsers();
        assertEquals(expected, result);
    }

    @Test
    void errorViewingAllUsers() {
        List<User> emptyList = List.of();

        when(userRepository.getAllUsers()).thenReturn(emptyList);

        String result = userCatalog.viewAllUsers();
        assertEquals(NO_USERS_FOUND, result);
    }

}
