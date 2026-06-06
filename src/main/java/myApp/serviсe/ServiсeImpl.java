package myApp.serviсe;

import org.springframework.transaction.annotation.Transactional;
import myApp.dao.Dao;
import myApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiсeImpl implements Serviсe {

    @Autowired
    private Dao daoImp;

    @Transactional
    @Override
    public void saveUser(String firstName, String lastNAme, int year) {
        daoImp.saveUser(firstName, lastNAme, year);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        daoImp.deleteUser(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        daoImp.updateUser(user);
    }

    @Override
    public User getUser(long id) {
        return daoImp.getUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return daoImp.getAllUsers();
    }
}
