package com.cursosapi.springsecurity.config.security;

import com.cursosapi.springsecurity.config.security.filter.JwtAuthenticationFilter;
import com.cursosapi.springsecurity.persistence.util.RolePermssion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class HTTPSECURITYCONFIG {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private AuthenticationProvider daoProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessMagConfig -> sessMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(daoProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authReqConfig -> {

                    // Autorizacion de endpoints de products

                    authReqConfig.requestMatchers(HttpMethod.GET, "/products")
                            .hasAuthority(RolePermssion.READ_ALL_PRODUCTS.name());

                    authReqConfig.requestMatchers(HttpMethod.GET, "/products/{productId}")
                            .hasAuthority(RolePermssion.READ_ONE_PRODUCTS.name());

                    authReqConfig.requestMatchers(HttpMethod.POST, "/products")
                            .hasAuthority(RolePermssion.CREATE_ONE_PRODUCTS.name());


                    authReqConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}/disabled")
                            .hasAuthority(RolePermssion.CREATE_ONE_PRODUCTS.name());
                    authReqConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}")
                            .hasAuthority(RolePermssion.DISABLE_ONE_PRODUCTS.name());


                    //Auroizacion de endpoints de catgorias

                    authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
                    authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
                    authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();

                    authReqConfig.anyRequest().authenticated();
                })
                .build();
    }
}
