package com.example.hiservice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;


public class AuthorizationFilter extends BasicAuthenticationFilter {

    public AuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

 	@Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        System.out.println("inside the dofilter");
        System.out.println(header);
        if (header == null) {
            chain.doFilter(request, response);
            
        }else {
        	  UsernamePasswordAuthenticationToken authentication = authenticate(request);
              SecurityContextHolder.getContext().setAuthentication(authentication);
              chain.doFilter(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token=token.substring(7); //  

        if (token != null) {
            Claims user = Jwts.parser().setSigningKey(DatatypeConverter
            							.parseBase64Binary("zzeresdsfwererere"))
            						.parseClaimsJws(token).getBody();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }else{
                return  null;
            }

        }
        return null;
    }
}
