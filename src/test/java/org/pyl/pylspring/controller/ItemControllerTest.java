package org.pyl.pylspring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.pyl.pylspring.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // todo: enleve order
    @Test
    @Order(1)
    public void getItemsTest() throws Exception {
        mockMvc.perform(get("/items")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("toto")));
    }

    @Test
    @Order(2)
    public void getItemTotoTest() throws Exception {
        mockMvc.perform(get("/items/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("titi")));
    }


    @Test
    @Order(3)
    public void createPloufItemTest() throws Exception {
        mockMvc.perform(post("/items")
                .content(asJsonString(new ItemDTO(0L, "plouf", "44", "")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("plouf")))
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.regionName", is("Grand Est")));
    }

    @Test
    @Order(4)
    public void updateTitiItemTest() throws Exception {
        mockMvc.perform(put("/items")
                .content(asJsonString(new ItemDTO(2L, "plop", "44", "")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("plop")))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.regionName", is("Grand Est")));
    }

    @Test
    @Order(5)
    public void deleteItemId2Test() throws Exception {
        mockMvc.perform(delete("/items/2"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(2)));

        mockMvc.perform(get("/items/2"))
                .andExpect(status().is4xxClientError());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
