package com.ws.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name = "product")
@Entity
public class ProductEntity extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String miniCode;
    private String name;
    private String description;
    private BigDecimal costSoles;
    private BigDecimal costDollars;
    private BigDecimal technicalPrice;
    private BigDecimal finalCost;
    private BigDecimal profitableNessCCT;
    private BigDecimal profitableNessCCC;
    private Long stock;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeEntity type;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;


    @ManyToOne
    @JoinColumn(name = "material_id")
    private MaterialEntity material;


    @OneToOne
    @JoinColumn(name = "store_id")
    private StoreEntity store;


    @Column(columnDefinition = "boolean default true")
    private Boolean status = true;

    @OneToOne
    @JoinColumn(name = "product_id")
    private HeadquartersEntity headquarters;



}
