package auctionapp.security;

import auctionapp.manager.UserManager;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter implements Filter {
    private static String signingKey = "lfdAmcWptKEeX89Mg2DvRhz3zj5D8mtN62QEvcy5m7EsguxVDGQ-MSHquOryUkBefPKJGPFYyFggtHUyu8hP8M5Xv7vvgNdfU0PvxfgceBJkcNgbdEggjqYfRJ5s-QlBwT-_BeiDCH_RqGxyHErHEfjtn5JdCvSDp4vZfA6oUcPPRDu563ghPFP1hTpiSdSAwYHdZeYXjCu8XoaerHcqIsZux-VvNaLjDicBj6tBqvG7lspikxwzbPiFV3sbmTC1FOC_LSJA5SshwCd6gj2MaWjIairwIvALHM3KZthBVfYPrYH8VsXiIj3kR347FYt0zBUZsspoJts7N-T2ALZprw";
    private UserManager userManager;
    @Autowired
    public JwtFilter() {
        this.userManager = userManager;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String header = httpServletRequest.getHeader("authorization");
        if(httpServletRequest == null || !header.startsWith("Bearer ")) {
            throw new ServletException("Authorization header is empty or don't starts with \"Bearer \"");
        }
        else {
            String token = header.substring(7);
            Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
            servletRequest.setAttribute("claims",claims);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
