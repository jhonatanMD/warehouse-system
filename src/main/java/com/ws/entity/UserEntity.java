package com.ws.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Data
@Table(name = "user")
@Entity
public class UserEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user;
    private String password;
    @Column(columnDefinition = "boolean default true")
    private Boolean status = true;


    @OneToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;


    @OneToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}
