package auctionapp.manager;

import auctionapp.dao.PersonRepo;
import auctionapp.dao.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonManager {

    private PersonRepo personRepo;

    @Autowired
    public PersonManager(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public Iterable<Person> getAllPesron() {
        return personRepo.findAll();
    }

    public String savePerson(Person person) {
        if(personRepo.save(person) != null) return "Person saved sucessfull";
        else return "Person does not saved";
    }

    public Optional<Person> findPersonById(Integer id) {
        return personRepo.findById(id);
    }

    public List<Person> findPersonByUserLogin(String login) {
        return personRepo.findPersonByUserLogin(login);
    }

    public String getEmailFromPesronByUserId(Integer userId) {
        Optional<Person> person = personRepo.findPersonByUserId(userId);
        if(person.isPresent()) {
            return person.get().getEmail();
        } else throw new NullPointerException("Person by user id not found");
    }

    public String getNameFromPesronByUserId(Integer userId) {
        Optional<Person> person = personRepo.findPersonByUserId(userId);
        if(person.isPresent()) {
            return person.get().getName();
        } else throw new NullPointerException("Person by user id not found");
    }

}
