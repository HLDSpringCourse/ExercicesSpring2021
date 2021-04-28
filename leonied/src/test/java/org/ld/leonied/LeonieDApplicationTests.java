package org.ld.leonied;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	ObjectMapper objectMapper;

	@Autowired
	private OrderService orderService;

	@Test
	void testGetOrder() throws Exception {
		orderService.getOrders().clear();
		orderService.addOrder(new Order("Léonie", "Lille"));

		mvc.perform(get("/orders/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("Léonie")))
				.andExpect(jsonPath("$.city", is("Lille")));
	}

	@Test
	void testAddOrder() throws Exception {
		Order order = new Order("Alain", "Lyon");

		mvc.perform(post("/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(order))
				)
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("Alain")))
				.andExpect(jsonPath("$.city", is("Lyon")));
	}

	@Test
	void testGetOrderByName() throws Exception {
		orderService.getOrders().clear();
		orderService.addOrder(new Order("Edouard", "Tours"));
		orderService.addOrder(new Order("Achille", "Tours"));
		orderService.addOrder(new Order("Edouard", "Tours"));

		mvc.perform(get("/orders/search?name=Edouard")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	void testGetOrderByCity() throws Exception {
		orderService.getOrders().clear();
		orderService.addOrder(new Order("Fanny", "Paris"));
		orderService.addOrder(new Order("Suzon", "Paris"));
		orderService.addOrder(new Order("Christine", "Orléans"));

		mvc.perform(get("/orders/search?city=Paris")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	void testGetOrderByNameAndCity() throws Exception {
		orderService.getOrders().clear();
		orderService.addOrder(new Order("Caroline", "Saint-Malo"));
		orderService.addOrder(new Order("Hannah", "Saint-Malo"));
		orderService.addOrder(new Order("Aline", "Saint-Malo"));
		orderService.addOrder(new Order("Rafaël", "Rennes"));

		mvc.perform(get("/orders/search?city=Saint-Malo&name=line")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	void testGetOrderByCityLat() throws Exception {
		orderService.getOrders().clear();
		orderService.addOrder(new Order("Anne-Cécile", 45, 4));
		orderService.addOrder(new Order("Mahault", 45, 4));
		orderService.addOrder(new Order("Maximilien", 49, 2));

		mvc.perform(get("/orders/search?lattitude=49")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)));
	}

	@Test
	void testDeleteOrder() throws Exception {
		orderService.getOrders().clear();
		orderService.addOrder(new Order("Léa", "Ascq"));
		orderService.addOrder(new Order("Léo", "Ascq"));
		orderService.addOrder(new Order("Luc", "Ascq"));

		mvc.perform(delete("/orders/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));
	}

	@Test
	void testDeleteOrderNotFound() throws Exception {
		orderService.getOrders().clear();
		orderService.addOrder(new Order("Nina", "Rouen"));
		orderService.addOrder(new Order("Nino", "Rouen"));
		orderService.addOrder(new Order("Ninon", "Rouen"));

		mvc.perform(delete("/orders/5")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void testUpdateOrder() throws Exception {
		orderService.getOrders().clear();
		orderService.addOrder(new Order("Avril", "Rouen"));
		orderService.addOrder(new Order("Marcus", "Rouen"));
		orderService.addOrder(new Order("Arrow", "Rouen"));

		Order order = orderService.findOrderById(2);
		order.setName("Pirate");

		mvc.perform(put("/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(order))
				)
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("Pirate")))
				.andExpect(jsonPath("$.city", is("Rouen")));
	}

}
