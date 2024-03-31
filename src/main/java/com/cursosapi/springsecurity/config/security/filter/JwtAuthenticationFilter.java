package com.cursosapi.springsecurity.config.security.filter;

import com.cursosapi.springsecurity.exception.ObjectNorFoundExeption;
import com.cursosapi.springsecurity.persistence.entity.User;
import com.cursosapi.springsecurity.services.UserServices;
import com.cursosapi.springsecurity.services.auth.JwtServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtServices jwtServises;
    @Autowired
    private UserServices userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Entro en el filtro ");

            // 1) .Obtener encabezado HTTP llamdo Autorization
            String authorizationHeader = request.getHeader("Authorization");
            //validar que tenga texto
            if (!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){
                     filterChain.doFilter(request,response);
                     return;

            }
            // 2) Obtener token JWT desde el encabezado
            String jwt = authorizationHeader.split("Bearer ")[1];

            // 3) Obtener el subjej/username desde el token
            String username = jwtServises.extractUsername(jwt);
            // 4) Setear objeto authentication dentro de securtity contex holder
            User usernameDetails = userService.findOneByUsername(username)
                    .orElseThrow(()-> new ObjectNorFoundExeption("User not Found : "+username));
            UsernamePasswordAuthenticationToken  authtoken = new UsernamePasswordAuthenticationToken(
                    username,null,usernameDetails.getAuthorities()
            );
            authtoken.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authtoken);
            // 5) Ejecutar el registro de filtrtos
            filterChain.doFilter(request,response);



    }
}
