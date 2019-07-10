package auctionapp.dao;

import auctionapp.dao.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepo extends CrudRepository<Person, Integer> {
    List<Person> findPersonByUserLogin(String login);
    Optional<Person> findPersonByUserId(Integer id);
}
