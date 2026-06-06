package myApp;

import myApp.config.HibernateConfig;
import myApp.dao.DaoImpl;
import myApp.model.User;
import myApp.serviсe.Serviсe;
import myApp.serviсe.ServiсeImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, ServiсeImpl.class, DaoImpl.class})
@Transactional
public class UserControllerTest {

    @Autowired
    private Serviсe serviceUser;
    private final String testName = "Ф";
    private final String testLastName = "Ы";
    private final Integer testAge = 31;

    @BeforeEach
    @DisplayName("Очиста таблицы")
    public void cleanUp() {
        List<User> users = serviceUser.getAllUsers();
        for (User user : users) {
            serviceUser.deleteUser(user.getId());
        }
    }


    @Test()
    @DisplayName("Сохранение юзера")
    public void saveUserTest() {
        try {
            serviceUser.saveUser(testName , testLastName, testAge);
            User savedUser = serviceUser.getAllUsers().get(0);
            if (!testName.equals(savedUser.getFirstName()) || !testLastName.equals(savedUser.getLastName())) {
                Assertions.fail("Ошибка при сохранении юзера!");
            }
        } catch (Exception e) {
            Assertions.fail("Ошибка при сохранении юзера!\n " + e);
        }
    }

    @Test
    @DisplayName("Удаление юзера")
    public void deleteUserTest() {
        try {
            serviceUser.saveUser(testName , testLastName, testAge);
            long id = serviceUser.getAllUsers().get(0).getId();
            serviceUser.deleteUser(id);
            List<User> allUser = serviceUser.getAllUsers();
            if (!allUser.isEmpty()) {
                Assertions.fail("Ошибка при удалении юзера!");
            }
        } catch (Exception e) {
            Assertions.fail("Ошибка при удалении юзера!!\n " + e);
        }
    }

    @Test
    @DisplayName("Получение всех юхеров")
    public void getAllUserTest() {
        try {
            serviceUser.saveUser(testName , testLastName, testAge);
            List<User> allUser = serviceUser.getAllUsers();
            if (allUser.size() != 1) {
                Assertions.fail("Ошибка при получении юзеров!");
            }
        } catch (Exception e) {
            Assertions.fail("Ошибка при получении юзеров!\n " + e);
        }
    }

    @Test
    @DisplayName("Изменение юзера")
    public void updateUserTest() {
        try {
            serviceUser.saveUser(testName , testLastName, testAge);
            long id = serviceUser.getAllUsers().get(0).getId();
            User updateUser = new User("Test", "Test", 1);
            updateUser.setId(id);
            serviceUser.updateUser(updateUser);
            User userDb = serviceUser.getUser(id);
            if (userDb.getFirstName().equals(testName) || userDb.getLastName().equals(testLastName) || userDb.getYear()==testAge ) {
                Assertions.fail("Ошибка при обновлении юзера!");
            }
        } catch (Exception e) {
            Assertions.fail("Ошибка при обновлении юзера!\n " + e);
        }
    }
}