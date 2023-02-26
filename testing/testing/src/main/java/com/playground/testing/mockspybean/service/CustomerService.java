package com.playground.testing.mockspybean.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playground.testing.mockspybean.domain.customer.Customer;
import com.playground.testing.mockspybean.domain.customer.CustomerRepository;
import com.playground.testing.mockspybean.domain.order.CustomerOrder;
import com.playground.testing.mockspybean.domain.order.CustomerOrderRepository;
import com.playground.testing.mockspybean.domain.order.OrderProductMap;
import com.playground.testing.mockspybean.domain.product.Product;
import com.playground.testing.mockspybean.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerOrderRepository customerOrderRepository;
    private final HttpSession httpSession;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional(readOnly = true)

    public long findMyOrderPriceSum() {

        Customer customer = (Customer) httpSession.getAttribute("loginUser");

        return customerOrderRepository.findAllByCustomer(customer)
                .mapToLong(co -> sum(co.getProducts()))
                .sum();
    }

    private long sum(List<OrderProductMap> orderProductMaps) {
        return orderProductMaps.stream()
                .mapToLong(m -> m.getProduct().getPrice())
                .sum();
    }

    @Transactional(readOnly = true)
    public void saveMyOrderProductCountInSession() {
        Customer customer = (Customer) httpSession.getAttribute("loginUser");
        int count = customerOrderRepository.findAllByCustomer(customer)
                .mapToInt(co -> co.getProducts().size())
                .sum();

        httpSession.setAttribute("orderCount", count);
    }

    @Transactional(readOnly = true)
    public String getCustomerJsonString(String requestBody, Customer customer) throws IOException {
        // objectMapper.readValue()는 spying 예정
        Customer readCustomer = objectMapper.readValue(requestBody, Customer.class);

        System.out.println("is readCustomer equal to customer = " + readCustomer.equals(customer));

        List<Product> products = customerOrderRepository.findTopByCustomer(customer)
                .map(o -> o.getProducts().stream()
                        .map(OrderProductMap::getProduct)
                        .collect(Collectors.toList())
                )
                .orElse(new ArrayList<>());

        // objectMapper.writeValueAsString()는 기능 유지
        return objectMapper.writeValueAsString(new OrderResponseDto(customer.getName(), products));
    }
}
