package com.ws.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Data
@Table(name = "sale")
@Entity
public class SaleEntity  extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String saleCod;
    private String srs;
    private String address;
    private String payCondition;
    private String referralGuide;
    private String customerDoc;
    private BigDecimal pSubTotal;
    private BigDecimal pIgv;
    private BigDecimal pTotal;
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "headquarters_id")
    private HeadquartersEntity headquarters;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "saleDetails_id")
    private List<SaleDetailEntity> saleDetails;


}
