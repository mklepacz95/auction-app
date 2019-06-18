package auctionapp.api;

import auctionapp.dao.entity.User;
import auctionapp.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userAPI")
public class UserApi {

    private UserManager userManager;

    @Autowired
    public UserApi(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping("/users")
    public Iterable<User> getAll() {
        return userManager.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> findUserById(@PathVariable Integer id) {
        return userManager.findUserById(id);
    }

    @PostMapping("/user")
    public String saveUser(@RequestBody User user) {
        return userManager.saveUser(user);
    }

    @GetMapping("/user/login/{login}")
    public List<User> findUserByLogin(@PathVariable String login) {
        return userManager.findUserByUsername(login);
    }

}
