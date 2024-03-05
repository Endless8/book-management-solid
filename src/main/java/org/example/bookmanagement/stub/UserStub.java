package org.example.bookmanagement.stub;

import org.example.bookmanagement.formatter.MessageFormatter;
import org.example.bookmanagement.formatter.MessageFormatterImp;
import org.example.bookmanagement.users.*;
import org.example.bookmanagement.dao.User;
import org.example.bookmanagement.dao.UserImp;
import org.example.bookmanagement.datasource.*;
import org.example.bookmanagement.repository.IdGeneratorImp;
import org.example.bookmanagement.repository.UserRepository;
import org.example.bookmanagement.repository.UserRepositoryImp;
import org.example.bookmanagement.wrapper.MapperWrapper;
import org.example.bookmanagement.wrapper.MapperWrapperImp;

public class UserStub {

    private static final MapperWrapper MAPPER_WRAPPER = new MapperWrapperImp();
    private static final DatasourceFactory DATASOURCE_FACTORY = new DatasourceFactoryImp();
    private static final DatasourceType USER_DATASOURCE_TYPE = new UserDatasourceType();
    private static final Datasource USER_DATASOURCE = DATASOURCE_FACTORY.createDatasource(MAPPER_WRAPPER, USER_DATASOURCE_TYPE);
    private static final UserRepository USER_REPOSITORY = new UserRepositoryImp(USER_DATASOURCE, MAPPER_WRAPPER);
    public static final IdGeneratorImp<User> ID_GENERATOR = new IdGeneratorImp<>(USER_REPOSITORY);
    public static final UserFactoryImp USER_FACTORY = new UserFactoryImp(ID_GENERATOR);
    private static final UserCreator USER_CREATOR = new UserCreatorImp(USER_FACTORY, USER_REPOSITORY);
    private static final MessageFormatter MESSAGE_FORMATTER = new MessageFormatterImp();
    private static final UserCatalog USER_CATALOG = new UserCatalogImp(USER_REPOSITORY, MESSAGE_FORMATTER);

    public static void main(String[] args) {
        //createUserByRepository();
        //createUserByCreator();
        viewUsers();
    }

    private static void viewUsers() {
        System.out.println(USER_CATALOG.viewAllUsers());
    }

    private static void createUserByCreator() {
        USER_CREATOR.insertNewUser("mimmo", "mim@email.com");
    }

    private static void createUserByRepository() {
        USER_REPOSITORY.save(new UserImp(1, "edoardo", "edo@email.com"));
    }
}
