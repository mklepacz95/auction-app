package auctionapp.manager;

import auctionapp.dao.PersonRepo;
import auctionapp.dao.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManager {

    private PersonRepo personRepo;

    @Autowired
    public AccountManager(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public String saveAccount(Person person) {
        if(personRepo.save(person) != null) return "Accont create sucessfull";
        else return "Account create not create";
    }

}
