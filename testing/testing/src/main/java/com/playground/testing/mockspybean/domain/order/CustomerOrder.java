package com.playground.testing.mockspybean.domain.order;

import com.playground.testing.mockspybean.domain.customer.Customer;
import com.playground.testing.mockspybean.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime orderDateTime;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Boolean isGiftPackaging;

    private String memo;

    @OneToOne
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "FK_ORDER_CUSTOMER"))
    private Customer customer;

    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProductMap> products = new ArrayList<>();

    @Builder
    public CustomerOrder(LocalDateTime orderDateTime, String address, Boolean isGiftPackaging, String memo, Customer customer, List<OrderProductMap> products) {
        this.orderDateTime = orderDateTime;
        this.address = address;
        this.isGiftPackaging = isGiftPackaging;
        this.memo = memo;
        this.customer = customer;
        this.products = products;
    }

    public void addProduct(Product product) {
        if (this.products == null) {
            this.products = new ArrayList<>();
        }

        this.products.add(new OrderProductMap(this, product));
    }
}
