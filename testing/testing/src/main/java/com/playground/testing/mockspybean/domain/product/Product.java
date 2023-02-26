package com.playground.testing.mockspybean.domain.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long price;

    @Column(nullable = false)
    private LocalDate manufactureDate;

    @Builder
    public Product(String name, long price, LocalDate manufactureDate) {
        this.name = name;
        this.price = price;
        this.manufactureDate = manufactureDate;
    }
}
