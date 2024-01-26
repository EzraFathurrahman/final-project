package com.phincon.bootcamp.springquiz4ezra.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResponseTest {
    @Test
    public void testResponse() {
        String status= "success";

        Response response = new Response();
        response.setResponse(status);


        assertEquals(status, response.getResponse());

    }
}