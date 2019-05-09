package auctionapp.api;

import auctionapp.dao.entity.Person;
import auctionapp.manager.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/personAPI")
public class PersonApi {

    private PersonManager personManager;

    @Autowired
    public PersonApi(PersonManager personManager) {
        this.personManager = personManager;
    }

    @GetMapping("/persons")
    public Iterable<Person> getAllPerson() {
        return personManager.getAllPesron();
    }


    @GetMapping("/person/{id}")
    public Optional<Person> getPersonById(@PathVariable Integer id) {
        return personManager.findPersonById(id);
    }

}
