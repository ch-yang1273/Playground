package com.playground.testing.mockspybean.service;

import com.playground.testing.mockspybean.domain.customer.Customer;
import com.playground.testing.mockspybean.domain.order.CustomerOrder;
import com.playground.testing.mockspybean.domain.order.CustomerOrderRepository;
import com.playground.testing.mockspybean.domain.product.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import javax.servlet.http.HttpSession;
import java.util.stream.Stream;

@Disabled("the spying on HttpSession doesn't work")
@SpringBootTest
public class CustomerServiceSpyTestDisabled {
    @Autowired
    private CustomerService customerService;

    @MockBean(name = "customerOrderRepository")
    private CustomerOrderRepository customerOrderRepository;

    /**
     * https://github.com/spring-projects/spring-boot/issues/10381
     * HttpSession은 @SpyBean으로 대체 불가능!
     * Thanks. The problem is that HttpSession isn't registered as a bean.
     * The Framework registers a SessionObjectFactory by calling beanFactory.registerResolvableDependency
     * which is "intended for factory/context references that are supposed to be autowirable but are not defined as beans in the factory".
     * Unfortunately, this means the spying on HttpSession doesn't work as you'd like.
     * There's no way for the @SpyBean infrastructure to find and replace the HttpSession bean is there isn't one.
     * This causes it to create an HttpSession bean which, along with the resolvable dependency defined by the Framework, causes the NoUniqueBeanDefinitionException.
     */
    @SpyBean(name = "httpSession")
    private HttpSession httpSession;

    @Test
    public void saveMyOrderProductCountInSession () throws Exception {

        // given
        Customer customer = new Customer();

        // httpSession.getAttribute("loginUser") 은 customer 를 반환한다.
        BDDMockito.given(httpSession.getAttribute("loginUser"))
                .willReturn(customer);

        CustomerOrder order = new CustomerOrder();
        order.addProduct(new Product());
        order.addProduct(new Product());

        // customerOrderRepository.findAllByCustomer(customer) 은 Stream.of(order) 를 반환한다.
        BDDMockito.given(customerOrderRepository.findAllByCustomer(customer))
                .willReturn(Stream.of(order));

        // when
        customerService.saveMyOrderProductCountInSession();

        // httpSession 이 @SpyBean 이어서 getAttribute("orderCount")을 사용할 수 있다.
        int count = (Integer) httpSession.getAttribute("orderCount");

        // then
        Assertions.assertThat(count).isEqualTo(2);
    }
}
