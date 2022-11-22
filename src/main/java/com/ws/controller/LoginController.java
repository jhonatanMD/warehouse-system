package com.ws.controller;

import com.ws.business.ILoginBusiness;
import com.ws.entity.dto.LoginDto;
import com.ws.entity.dto.data.CompanyResponse;
import com.ws.entity.dto.data.EmployeeResponse;
import com.ws.entity.dto.data.HeadquartersResponse;
import com.ws.entity.dto.data.JwtDataResponse;
import com.ws.entity.dto.data.RoleResponse;
import com.ws.service.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class LoginController {

    private final ILoginBusiness loginBusiness;

    @PostMapping("/login")
    public Mono<JwtDataResponse> jwt(@RequestBody LoginDto login){
        return  getJWTToken(login);
    }

    @PostMapping("/refresh")
    public Mono<JwtDataResponse> refresh(@RequestAttribute("user") String user, @RequestAttribute("password") String pass){
        return getJWTToken(new LoginDto(user,pass));
    }


    private Mono<JwtDataResponse> getJWTToken(LoginDto login) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        return loginBusiness.getLogin(login.getUsername(),login.getPassword())
                .map(userData ->  JwtDataResponse.builder().jwt(Jwts
                            .builder()
                            .setId("BYTES")
                            .claim("authorities",
                                    grantedAuthorities.stream()
                                            .map(GrantedAuthority::getAuthority)
                                            .collect(Collectors.toList()))
                            .claim("data",userData)
                            .claim("headquarters",userData.getEmployee().getHeadquarters().getId())
                            .claim("company",userData.getEmployee().getHeadquarters().getCompany().getId())
                            .claim("ruc",userData.getEmployee().getHeadquarters().getCompany().getRut())
                            .claim("fullName",userData.getEmployee().getName() + " " + userData.getEmployee().getLastName())
                            .claim("user",login.getUsername())
                            .claim("password",login.getPassword())
                            .setIssuedAt(new Date(System.currentTimeMillis()))
                            .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                            .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact())
                        .employee(new EmployeeResponse(userData.getEmployee()))
                        .headquarters(new HeadquartersResponse(userData.getEmployee().getHeadquarters()))
                        .company(new CompanyResponse(userData.getEmployee().getHeadquarters().getCompany()))
                        .roles(new RoleResponse(userData.getRole())).build())
                .flatMap(res ->  Mono.just(res.getRoles())
                            .flatMap(role -> loginBusiness.getPermissionRole(role.getId())
                            .collectList()
                            .map(permissionRole -> {
                                role.setPermissionRole(permissionRole);
                                return role;
                            })).map(roles -> {
                                res.setRoles(roles);
                                return res;
                            }));
    }
}
