package com.playground.testing.mockspybean.domain.order;

import com.playground.testing.mockspybean.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class OrderProductMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_order_id", foreignKey = @ForeignKey(name = "FK_CUSTOMER_ORDER_MAP"))
    private CustomerOrder customerOrder;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_PRODUCT_MAP"))
    private Product product;

    public OrderProductMap(CustomerOrder customerOrder, Product product) {
        this.customerOrder = customerOrder;
        this.product = product;
    }
}
