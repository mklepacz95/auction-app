package auctionapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter implements Filter {

    private static String signingKey = "l2fvExEcqKRcF-EFVgOEtN65aocBw4MaAGOeX3i57-5zVMsUcpvHmFGKedqPCMxGyRBMoDKITlokxsq5_bMIuIFm7_LT_IJdBHwyxCfkn4TnHWmXO8r3UDFm3yEJpowSYgn8dlALn0JlMYgh-u7BLDIKgIzhYdRGNvrJqm6MMSPJxElZraAPXTij5bsAWkn2PiErZR49DgzGu0KLPPwEkt6blbxxa0kie9xiJ7O0HfvwnqcJLUo-0DVIBY1BcNDI3_fhl8lrJCU3AazmT0CW3JI90LCHJQWE0xDW4WD5NpwVFrpRNIRGSeW_CFO93PEhfdqWrEr3golgS2V2gExbTw";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException { HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if(!httpServletRequest.getMethod().equals("OPTIONS")) {
            String header = httpServletRequest.getHeader("authorization");
            if (httpServletRequest == null || !header.startsWith("Bearer ")) {
                throw new ServletException("Authorization header is empty or don't starts with \"Bearer \"");
            } else {
                String token = header.substring(7);
                Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
                servletRequest.setAttribute("claims", claims);
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
