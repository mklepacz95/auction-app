package auctionapp.api;

import auctionapp.dao.entity.User;
import auctionapp.manager.UserManager;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
public class LoginApi {

    private UserManager userManager;
    private static final String signKey = "l2fvExEcqKRcF-EFVgOEtN65aocBw4MaAGOeX3i57-5zVMsUcpvHmFGKedqPCMxGyRBMoDKITlokxsq5_bMIuIFm7_LT_IJdBHwyxCfkn4TnHWmXO8r3UDFm3yEJpowSYgn8dlALn0JlMYgh-u7BLDIKgIzhYdRGNvrJqm6MMSPJxElZraAPXTij5bsAWkn2PiErZR49DgzGu0KLPPwEkt6blbxxa0kie9xiJ7O0HfvwnqcJLUo-0DVIBY1BcNDI3_fhl8lrJCU3AazmT0CW3JI90LCHJQWE0xDW4WD5NpwVFrpRNIRGSeW_CFO93PEhfdqWrEr3golgS2V2gExbTw";

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
            response = Jwts.builder()
                    .setSubject(user.getLogin())
                    .claim("role","user")
                    .setIssuedAt(new Date(currentMilis))
                    .setExpiration(new Date(currentMilis + 1200000))
                    .signWith(SignatureAlgorithm.HS256, signKey).compact();
        }

        return response;
    }

}
