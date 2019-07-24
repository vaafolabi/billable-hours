package com.binance.assignments;

import com.binance.assignments.controllers.GuestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceApplicationTests {

    @Autowired
    private GuestController guestController;


    @Test
    public void contextLoads() {
        assertThat(guestController).isNotNull();
        assertEquals("guest", guestController.guest());
    }

   }
