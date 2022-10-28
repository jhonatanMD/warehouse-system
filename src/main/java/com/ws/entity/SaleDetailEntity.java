package com.ws.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name = "sale_detail")
@Entity
public class SaleDetailEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private ProductEntity product;
    private String productDetail;
    private BigDecimal priceUnit;
    private Long amount;
    private BigDecimal pTotal;

}
