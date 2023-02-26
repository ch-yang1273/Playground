package com.playground.testing.mockspybean.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playground.testing.mockspybean.domain.customer.Customer;
import com.playground.testing.mockspybean.domain.customer.CustomerRepository;
import com.playground.testing.mockspybean.domain.order.CustomerOrder;
import com.playground.testing.mockspybean.domain.order.CustomerOrderRepository;
import com.playground.testing.mockspybean.domain.product.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Optional;

@SpringBootTest
public class CustomerServiceSpyTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @MockBean(name = "customerOrderRepository")
    private CustomerOrderRepository customerOrderRepository;

    @SpyBean(name = "objectMapper")
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("문자열 반환")
    public void getCustomerJsonString_JSON() throws IOException {

        // given
        Customer customer = Customer.builder()
                .name("ych")
                .build();

        final String REQUEST_PARAM = objectMapper.writeValueAsString(customer);

        CustomerOrder order = new CustomerOrder();

        order.addProduct(Product.builder()
                .price(10000L)
                .build());

        order.addProduct(Product.builder()
                .price(15000L)
                .build());

        BDDMockito.given(customerOrderRepository.findTopByCustomer(customer))
                .willReturn(Optional.of(order));

        BDDMockito.given(objectMapper.readValue(REQUEST_PARAM, Customer.class))
                .willReturn(customer);

        // when
        final String customerJsonString = customerService.getCustomerJsonString(REQUEST_PARAM, customer);

        //then
        Assertions.assertThat(customerJsonString).isEqualTo("{\"customerName\":\"ych\",\"products\":[{\"name\":null,\"price\":10000},{\"name\":null,\"price\":15000}]}");
    }
}
