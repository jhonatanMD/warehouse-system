package com.ws.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Table(name = "permission_role")
@Entity
public class PermissionRoleEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean i;
    private Boolean c;
    private Boolean u;
    private Boolean d;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private ModuleEntity modules;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @Column(columnDefinition = "boolean default true")
    private Boolean status = true;


}
