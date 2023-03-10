package com.playground.testing.staticmock;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class MockingStaticTest {

    MyService myService;

    @Test
    void mock_LocalDateTime_Now() {
        // 안티 패턴 입니다.
        // Need library! : testImplementation 'org.mockito:mockito-inline'
        // ref : https://thalals.tistory.com/399
        // ref : https://stackoverflow.com/questions/4482315/why-doesnt-mockito-mock-static-methods/62925944#62925944
        LocalDateTime thatDate = LocalDateTime.of(1900, 1, 1, 0, 0);

        try (MockedStatic<LocalDateTime> mock = Mockito.mockStatic(LocalDateTime.class)) {
            mock.when(LocalDateTime::now).thenReturn(thatDate);
            assertThat(LocalDateTime.now()).isEqualTo(thatDate);
            System.out.println("in LocalDateTime.now = " + LocalDateTime.now());
            System.out.println("in thatDate = " + thatDate);
        }

        assertThat(LocalDateTime.now()).isNotEqualTo(thatDate);
        System.out.println("out LocalDateTime.now = " + LocalDateTime.now());
        System.out.println("out thatDate = " + thatDate);
    }

    @Test
    void mock_test() {
        // Mocking을 해도 이렇게...
        LocalDateTime thatDate = LocalDateTime.of(1900, 1, 1, 0, 0);

        MyLocalDateTime mock = Mockito.mock(MyLocalDateTime.class);
        myService = new MyService(mock);

        //given
//        BDDMockito.when(mock.now()).thenReturn(thatDate);
        BDDMockito.given(mock.now()).willReturn(thatDate);

        //when
        String result = myService.doFunction();

        //then
        assertThat(mock.now().toString()).isEqualTo(result);
    }
}
