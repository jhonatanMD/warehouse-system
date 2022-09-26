package com.ws.controller;

import com.ws.entity.dto.LoginDto;
import com.ws.service.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class LoginController {

    private final IUserService userService;

    @PostMapping("/login")
    public Mono<String> jwt(@RequestBody LoginDto login){
        return  getJWTToken(login);
    }


    private Mono<String> getJWTToken(LoginDto login) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        return userService.getLogin(login.getUsername(),login.getPassword())
                .map(userData ->  Jwts
                            .builder()
                            .setId("BYTES")
                            .claim("authorities",
                                    grantedAuthorities.stream()
                                            .map(GrantedAuthority::getAuthority)
                                            .collect(Collectors.toList()))
                            .claim("data",userData)
                            .claim("headquarters",userData.getEmployee().getHeadquarters().getId())
                            .claim("company",userData.getEmployee().getHeadquarters().getCompany().getId())
                            .claim("fullName",userData.getEmployee().getName() + " " + userData.getEmployee().getLastName())
                            .setIssuedAt(new Date(System.currentTimeMillis()))
                            .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                            .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact());
    }
}
