package com.ws.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "supplier_contact")
@Entity
public class SupplierContactEntity extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String phone;
    private String exhibit;
    private String email;
    private String obs;
    private String web;

    @Column(columnDefinition = "boolean default true")
    private Boolean status = true;

}
