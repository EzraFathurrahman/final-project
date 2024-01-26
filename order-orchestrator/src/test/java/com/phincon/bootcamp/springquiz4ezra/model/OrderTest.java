package com.phincon.bootcamp.springquiz4ezra.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderTest {
    @Test
    public void testOrder() {
        String id = "1";
        String product = "productA";
        String price = "10";
        String status = "PENDING";
        String action = "Registration-1";

        Order order = new Order();
        order.setId("1");
        order.setProduct("productA");
        order.setPrice("10");
        order.setStatus("PENDING");
        order.setAction("Registration-1");

        assertEquals(id, order.getId());
        assertEquals(product, order.getProduct());
        assertEquals(price, order.getPrice());
        assertEquals(status, order.getStatus());
        assertEquals(action, order.getAction());
    }
}