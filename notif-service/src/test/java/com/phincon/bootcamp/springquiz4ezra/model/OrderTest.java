package com.phincon.bootcamp.springquiz4ezra.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderTest {


    @Test
    public void testConstructorAndGetters() {
        String id = "123";
        String product = "Sabun";
        String price = "10.99";
        String status = "PENDING";
        String action = "CONFIRM";
        String step = "CONFIRM";
        String trxid = "CONFIRM";

        Order order=new Order();
        order.setId(id);
        order.setProduct(product);
        order.setPrice(price);
        order.setStatus(status);
        order.setAction(action);
        order.setStep(step);
        order.setTrxid(trxid);


        assertEquals(id, order.getId());
        assertEquals(product, order.getProduct());
        assertEquals(price, order.getPrice());
        assertEquals(status, order.getStatus());
        assertEquals(action, order.getAction());
        assertEquals(step, order.getStep());
        assertEquals(trxid, order.getTrxid());
    }
}