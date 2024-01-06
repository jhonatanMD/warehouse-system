package com.ws;

import com.ws.config.jwt.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class WarehouseSystemApplication {

    public static void main(String[] args) {




        SpringApplication.run(WarehouseSystemApplication.class, args);
    }
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**","/swagger-ui.html/**"
    };


    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/token/login").permitAll()
                    .antMatchers(HttpMethod.POST, "/import").permitAll()
                    .antMatchers(HttpMethod.GET, "/reports/**").permitAll()
                    .antMatchers(HttpMethod.GET, AUTH_WHITELIST).permitAll()
                   /* .antMatchers(HttpMethod.GET, "/api/generarReporteOrdenSalida").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/generarReporteOrdenCompra").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/consulta/consultar_voz").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/permisos/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/permisos/**").permitAll()
                    .antMatchers(HttpMethod.GET, AUTH_WHITELIST).permitAll()*/
                    .anyRequest().authenticated().and().cors();
        }
    }
}
