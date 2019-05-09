package auctionapp.manager;

import auctionapp.dao.UserRepo;
import auctionapp.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(userRepo.save(user) != null) return "User saved sucessfull";
        else return "User does not saved";
    }
}
