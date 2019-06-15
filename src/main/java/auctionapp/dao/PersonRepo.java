package auctionapp.dao;

import auctionapp.dao.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends CrudRepository<Person, Integer> {
    List<Person> findPersonByUserLogin(String login);
}
