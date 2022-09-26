package com.ws.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Table(name = "employee")
@Entity
public class EmployeeEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String secondSurName;
    @Column(columnDefinition = "boolean default true")
    private Boolean status = true;


    @OneToOne
    @JoinColumn(name = "headquarters_id")
    private HeadquartersEntity headquarters;
}
