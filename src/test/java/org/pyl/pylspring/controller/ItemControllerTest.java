package org.pyl.pylspring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pyl.pylspring.PylspringApplication;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.pyl.pylspring.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void getItemsTest() throws Exception {
        this.mockMvc.perform(get("/item")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("toto")));
    }

    @Test
    @Order(2)
    public void getItemTotoTest() throws Exception {
        this.mockMvc.perform(get("/item/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("titi")));
    }


    @Test
    @Order(3)
    public void createPloufItemTest() throws Exception {
        this.mockMvc.perform(post("/item")
                .content(asJsonString(new ItemDTO(0L, "plouf")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("plouf")))
                .andExpect(jsonPath("$.id", is(4)));
    }

    @Test
    @Order(4)
    public void updateTitiItemTest() throws Exception {
        this.mockMvc.perform(put("/item")
                .content(asJsonString(new ItemDTO(2L, "plop")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("plop")))
                .andExpect(jsonPath("$.id", is(2)));
    }

    @Test
    @Order(5)
    public void deleteItemId2Test() throws Exception {
        this.mockMvc.perform(delete("/item/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(2)));

        this.mockMvc.perform(get("/item/2")
                .contentType(MediaType.APPLICATION_JSON))
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
