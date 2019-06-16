package auctionapp.api;

import auctionapp.dao.entity.User;
import auctionapp.manager.UserManager;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
@CrossOrigin
public class LoginApi {

    private UserManager userManager;
    private static final String signKey = "lfdAmcWptKEeX89Mg2DvRhz3zj5D8mtN62QEvcy5m7EsguxVDGQ-MSHquOryUkBefPKJGPFYyFggtHUyu8hP8M5Xv7vvgNdfU0PvxfgceBJkcNgbdEggjqYfRJ5s-QlBwT-_BeiDCH_RqGxyHErHEfjtn5JdCvSDp4vZfA6oUcPPRDu563ghPFP1hTpiSdSAwYHdZeYXjCu8XoaerHcqIsZux-VvNaLjDicBj6tBqvG7lspikxwzbPiFV3sbmTC1FOC_LSJA5SshwCd6gj2MaWjIairwIvALHM3KZthBVfYPrYH8VsXiIj3kR347FYt0zBUZsspoJts7N-T2ALZprw";

    @Autowired
    public LoginApi(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        String response = null;
        long currentMilis = System.currentTimeMillis();
        if(userManager.findUserByUsername(user.getLogin()) == null) {
            response = "User doesnt exists";
        }
        else  {
            if(userManager.verifyPassowrdForUser(user.getLogin(), user.getPassword()))
            response = "Brearer " + Jwts.builder()
                    .setSubject(user.getLogin())
                    .claim("role","user")
                    .setIssuedAt(new Date(currentMilis))
                    .setExpiration(new Date(currentMilis + 1200000))
                    .signWith(SignatureAlgorithm.HS256, signKey).compact();
        }

        return response;
    }

}
