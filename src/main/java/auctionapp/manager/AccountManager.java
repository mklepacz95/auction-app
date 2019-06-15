package auctionapp.manager;

import auctionapp.dao.PersonRepo;
import auctionapp.dao.UserRepo;
import auctionapp.dao.entity.Person;
import auctionapp.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManager {

    private PersonRepo personRepo;
    private UserManager userManager;

    @Autowired
    public AccountManager(PersonRepo personRepo, UserManager userManager) {
        this.personRepo = personRepo;
        this.userManager = userManager;
    }

    public String saveAccount(Person person) {
        String response = null;
        User user = person.getUser();
        if(userManager.findUserByUsername(user.getLogin()).size() == 0) {
            if(personRepo.save(person) != null) response = "Accont create sucessfull";
            else response = "Account create not create";
        } else response = "Login is already used";
        return response;
    }
}
