package auctionapp.manager;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtManager {

    private static final String signKey = "l2fvExEcqKRcF-EFVgOEtN65aocBw4MaAGOeX3i57-5zVMsUcpvHmFGKedqPCMxGyRBMoDKITlokxsq5_bMIuIFm7_LT_IJdBHwyxCfkn4TnHWmXO8r3UDFm3yEJpowSYgn8dlALn0JlMYgh-u7BLDIKgIzhYdRGNvrJqm6MMSPJxElZraAPXTij5bsAWkn2PiErZR49DgzGu0KLPPwEkt6blbxxa0kie9xiJ7O0HfvwnqcJLUo-0DVIBY1BcNDI3_fhl8lrJCU3AazmT0CW3JI90LCHJQWE0xDW4WD5NpwVFrpRNIRGSeW_CFO93PEhfdqWrEr3golgS2V2gExbTw";

    public JwtManager() {
    }

    public String generateJwt(String login) {
        long currentMilis = System.currentTimeMillis();
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(login)
                .claim("role","user")
                .setIssuedAt(new Date(currentMilis))
                .setExpiration(new Date(currentMilis + 10800000))
                .signWith(SignatureAlgorithm.HS256, signKey).compact();
    }

    public String getLoginFromJwt(String header) {
        String jwt = header.substring(7);
        Claims claims = Jwts.parser().setSigningKey(signKey).parseClaimsJws(jwt).getBody();
        return claims.getSubject();
    }
}
