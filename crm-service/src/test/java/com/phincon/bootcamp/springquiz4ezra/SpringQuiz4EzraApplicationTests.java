package com.phincon.bootcamp.springquiz4ezra;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SpringQuiz4EzraApplicationTests {

	@Test
	void contextLoads() {
		assertThrows(IllegalArgumentException.class,()->SpringQuiz4EzraApplication.main(null));
	}

}
