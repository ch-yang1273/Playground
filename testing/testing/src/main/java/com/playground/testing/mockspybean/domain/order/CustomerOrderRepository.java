package com.playground.testing.mockspybean.domain.order;

import com.playground.testing.mockspybean.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    Stream<CustomerOrder> findAllByCustomer(Customer customer);
    Optional<CustomerOrder> findTopByCustomer(Customer customer);
}
