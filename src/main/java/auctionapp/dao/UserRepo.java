package auctionapp.dao;

import auctionapp.dao.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    List<User> findUserByLogin(String login);
    User findPasswordByLogin(String longin);
}
