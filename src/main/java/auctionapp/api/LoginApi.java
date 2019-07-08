package auctionapp.api;

import auctionapp.dao.entity.User;
import auctionapp.manager.JwtManager;
import auctionapp.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginApi {

    private UserManager userManager;


    @Autowired
    public LoginApi(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        String response = null;
        if(userManager.findUserByUsername(user.getLogin()) == null) {
            response = "User doesnt exists";
        }
        else  {
            if(userManager.verifyPassowrdForUser(user.getLogin(), user.getPassword()))
            response = new JwtManager().generateJwt(user.getLogin());
        }
        return response;
    }

}
