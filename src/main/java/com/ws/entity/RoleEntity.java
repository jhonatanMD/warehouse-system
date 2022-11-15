package com.ws.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Table(name = "role")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity  extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "boolean default true")
    private Boolean status = true;

    @OneToOne
    @JoinColumn(name = "headquarters_id")
    private HeadquartersEntity headquarters;

    public RoleEntity(Long id) {
        this.id = id;
    }
}
