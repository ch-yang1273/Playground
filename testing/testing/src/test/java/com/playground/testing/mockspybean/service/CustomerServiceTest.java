package com.playground.testing.mockspybean.service;

import com.playground.testing.mockspybean.domain.customer.Customer;
import com.playground.testing.mockspybean.domain.customer.CustomerRepository;
import com.playground.testing.mockspybean.domain.order.CustomerOrder;
import com.playground.testing.mockspybean.domain.order.CustomerOrderRepository;
import com.playground.testing.mockspybean.domain.product.Product;
import com.playground.testing.mockspybean.domain.product.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private HttpSession httpSession;

    @Test
    @DisplayName("로그인 사용자의 주문상품 금액 합계가 반환된다")
    public void findMyOrderPriceSum() throws Exception {

        Customer customer = customerRepository.save(
                Customer.builder()
                        .name("chanhong")
                        .email("ych1273@gmail.com")
                        .mobileNumber("01012345678")
                        .build()
        );

        httpSession.setAttribute("loginUser", customer);

        Product keyboard = productRepository.save(
                Product.builder()
                        .name("키보드")
                        .manufactureDate(LocalDate.of(2023, 2, 26))
                        .price(10000)
                        .build()
        );

        Product mouse = productRepository.save(
                Product.builder()
                        .name("마우스")
                        .manufactureDate(LocalDate.of(2023, 2, 26))
                        .price(15000)
                        .build()
        );

        CustomerOrder customerOrder = customerOrderRepository.save(
                CustomerOrder.builder()
                        .customer(customer)
                        .address("address")
                        .orderDateTime(LocalDateTime.of(2023,2,23,20,0))
                        .isGiftPackaging(true)
                        .build()
        );

        customerOrder.addProduct(keyboard);
        customerOrder.addProduct(mouse);

        customerOrderRepository.save(customerOrder);

        //when
        long sum = customerService.findMyOrderPriceSum();

        //then
        Assertions.assertThat(sum).isEqualTo(25000L);
    }
}