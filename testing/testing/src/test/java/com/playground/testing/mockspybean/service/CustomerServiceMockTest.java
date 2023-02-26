package com.playground.testing.mockspybean.service;

import com.playground.testing.mockspybean.domain.customer.Customer;
import com.playground.testing.mockspybean.domain.customer.CustomerRepository;
import com.playground.testing.mockspybean.domain.order.CustomerOrder;
import com.playground.testing.mockspybean.domain.order.CustomerOrderRepository;
import com.playground.testing.mockspybean.domain.product.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.servlet.http.HttpSession;
import java.util.stream.Stream;

@SpringBootTest
public class CustomerServiceMockTest {

    @Autowired
    private CustomerService customerService;

    @MockBean(name = "httpSession") // name 지정 안해주면 NoUniqueBean 에러!
    private HttpSession httpSession;

    @MockBean(name = "customerOrderRepository")
    private CustomerOrderRepository customerOrderRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("@MockBean 로그인 사용자의 주문상품 금액 합계가 반환된다")
    public void findMyOderPriceSum() {

        //given
        Customer customer = new Customer();

        BDDMockito.given(httpSession.getAttribute("loginUser"))
                .willReturn(customer);

        CustomerOrder order = new CustomerOrder();

        order.addProduct(
                Product.builder()
                        .price(10000L)
                        .build()
        );

        order.addProduct(
                Product.builder()
                        .price(15000L)
                        .build()
        );

        BDDMockito.given(customerOrderRepository.findAllByCustomer(customer))
                .willReturn(Stream.of(order));

        //when
        long sum = customerService.findMyOrderPriceSum();

        //then
        Assertions.assertThat(sum).isEqualTo(25000L);
    }
}
