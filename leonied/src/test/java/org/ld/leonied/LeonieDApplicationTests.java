package org.ld.leonied;

import org.junit.jupiter.api.*;
import org.ld.leonied.entity.Order;
import org.ld.leonied.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class LeonieDApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private OrderService orderService;

	@Test
	void testGetOrder()
			throws Exception {

		orderService.addOrder(new Order("plouf", "Lille"));

		System.out.println(orderService.getOrders().size());

		mvc.perform(get("/order/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("plouf")))
				.andExpect(jsonPath("$.city", is("Lille")));
	}

}
