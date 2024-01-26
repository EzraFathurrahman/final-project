package com.phincon.bootcamp.springquiz4ezra.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderTest {
    @Test
    public void testConstructorAndGetters() {
        String id = "123";
        String product = "Product A";
        String price = "10.99";
        String status = "PENDING";
        String action = "CONFIRM";
        String step = "CONFIRM";
        String trxid = "CONFIRM";

        Order order = new Order(id, product, price, status, action,step,trxid);

        assertEquals(id, order.getId());
        assertEquals(product, order.getProduct());
        assertEquals(price, order.getPrice());
        assertEquals(status, order.getStatus());
        assertEquals(action, order.getAction());
    }

}