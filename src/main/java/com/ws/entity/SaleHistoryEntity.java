package com.ws.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "sale_history")
@Entity
public class SaleHistoryEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long saleId;
    private Long productId;
    private Long amount;
    private Boolean status =  true;


}
