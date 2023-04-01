package study.object.chapter1;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class TheaterTest {

    @Test
    public void test() {
        // 판매 준비
        List<Ticket> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Ticket(1000L));
        }

        TicketOffice ticketOffice = new TicketOffice(0L, list);
        TicketSeller ticketSeller = new TicketSeller(ticketOffice);
        Theater theater = new Theater(ticketSeller);

        // 관객 준비
        Audience invitedAudience = new Audience(new Bag(1000L, new Invitation(LocalDateTime.now())));
        Audience audience = new Audience(new Bag(10000L));

        System.out.println(ticketOffice);
        System.out.println(invitedAudience.getBag());
        System.out.println(audience.getBag());

        //when
        theater.enter(invitedAudience);
        theater.enter(audience);

        System.out.println(ticketOffice);
        System.out.println(invitedAudience.getBag());
        System.out.println(audience.getBag());
    }
}