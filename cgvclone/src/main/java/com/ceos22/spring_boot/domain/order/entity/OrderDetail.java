package com.ceos22.spring_boot.domain.order.entity;

import com.ceos22.spring_boot.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class OrderDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long odId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private UserOrder userOrder;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;
    private Integer price;
    private Integer unitPrice;

    @Builder
    public OrderDetail(UserOrder userOrder, Product product, int quantity, int unitPrice, int subtotal) {
        this.userOrder = userOrder;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }


    // subtotal = quantity * price
    private Integer subtotal;
}
