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
import java.util.List;

@Data
@Table(name = "providers")
@Entity
public class ProvidersEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String ruc;
    @Column(columnDefinition = "boolean default true")
    private Boolean status = true;

    @OneToOne
    @JoinColumn(name = "headquarters_id")
    private HeadquartersEntity headquarters;

    @ManyToMany
    @JoinColumn(name = "supplierContacts_id")
    private List<SupplierContactEntity> supplierContacts;
}
