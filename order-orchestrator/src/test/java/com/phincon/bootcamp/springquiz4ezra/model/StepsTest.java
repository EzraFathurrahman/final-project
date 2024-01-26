package com.phincon.bootcamp.springquiz4ezra.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StepsTest {
    @Test
    public void testOrder() {
        String id = "1";
        String cfgId = "1";
        String step = "queue.crm";
        String priority = "2";
        String action = "Registration-crm";

        Steps steps = new Steps();
        steps.setId(id);
        steps.setCfgId(cfgId);
        steps.setStep(step);
        steps.setPriority(priority);
        steps.setAction(action);

        assertEquals(id, steps.getId());
        assertEquals(cfgId, steps.getCfgId());
        assertEquals(step, steps.getStep());
        assertEquals(priority, steps.getPriority());
        assertEquals(action, steps.getAction());
    }
}