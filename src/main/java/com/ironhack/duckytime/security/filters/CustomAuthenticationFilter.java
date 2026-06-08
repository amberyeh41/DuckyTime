package com.ironhack.duckytime.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.duckytime.dto.LoginRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j // (Simple Logging Facade for Java) offers logging API which is more professional that simply sout
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            LoginRequest loginRequest = mapper.readValue(request.getInputStream(), LoginRequest.class);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            ); // we will get a token

            log.info("Username is: {}", loginRequest.getUsername());
            log.info("Password is: {}", loginRequest.getPassword());

            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        // Creating an HMAC256 (Hash-based Message Authentication Code using SHA-512 algorithm)
        // encoded JWT with secret key
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        var authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        // Adding user details and roles to the token
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", authorities)
                .sign(algorithm);

        // Creating a map with the generated token
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);

        // Setting the response type to application/json
        response.setContentType(APPLICATION_JSON_VALUE);

        // Writing the token as response
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
