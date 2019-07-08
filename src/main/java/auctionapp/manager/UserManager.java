package auctionapp.manager;

import auctionapp.dao.UserRepo;
import auctionapp.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserManager {

    private UserRepo userRepo;

    @Autowired
    public UserManager(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public Optional<User> findUserById(Integer id) {
        return userRepo.findById(id);
    }

    public String saveUser(User user) {
        if (userRepo.save(user) != null) return "User saved sucessfull";
        else return "User does not saved";
    }

    public List<User> findUserByUsername(String login) {
        return userRepo.findUserByLogin(login);
    }

    public boolean verifyPassowrdForUser(String login, String password) {
        if(password.equals(userRepo.findPasswordByLogin(login).getPassword())) return true;
        else return false;
    }

    public String getPassowrdByLogin(String login) {
        return userRepo.findPasswordByLogin(login).getPassword();
    }

    public boolean userExisit(String login) {
        if(findUserByUsername(login).size() == 0) return false;
        else return true;
    }
}
