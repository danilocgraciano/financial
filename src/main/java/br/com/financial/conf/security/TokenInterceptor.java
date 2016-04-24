package br.com.financial.conf.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.financial.repository.UsuarioRepository;

public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UsuarioRepository repository;

    @Override
    public boolean preHandle( HttpServletRequest request , HttpServletResponse response , Object handler ) throws Exception{

        String url = ( (HttpServletRequest) request ).getRequestURL().toString();

        if ( !url.contains("/api/") )
            return true;

        // THE LOGIN PROCESS GENERATE DE TOKEN
        if ( url.contains("/api/login") )
            return true;

        // NEW USER TO START USING THE APP
        if ( url.contains("/api/account") )
            return true;

        final String authHeader = request.getHeader("Auth-Token");
        if ( authHeader == null || authHeader.equalsIgnoreCase("null") ){
            throw new ServletException("Missing or invalid Authorization header.");
        }

        final String token = authHeader;

        try{
            final Claims claims = Jwts.parser().setSigningKey(TokenHandler.SECRET_KEY).parseClaimsJws(token).getBody();

            if ( claims.getExpiration().before(new Date()) ){
                throw new ServletException("Expired Token.");
            }

            request.setAttribute("claims", claims);
        } catch ( final SignatureException e ){
            throw new ServletException("Invalid token.");
        }

        System.out.println("Got request to save data : name:" + request.getParameter("name"));
        return true;
    }

}
