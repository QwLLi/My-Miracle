package myApp.serviсe;

import myApp.model.User;

import java.util.List;

public interface Serviсe {

    void saveUser(String firstName , String lastNAme , int year);

    void deleteUser(long id);

    void updateUser(User user);

    User getUser(long id);

    List<User> getAllUsers();
}
