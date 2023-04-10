package com.aacademy.realestate.controller;

import com.aacademy.realestate.converter.FloorConverter;
import com.aacademy.realestate.dto.FloorDTO;
import com.aacademy.realestate.model.Floor;
import com.aacademy.realestate.service.FloorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(value = FloorController.class)
public class FloorControllerTest extends BaseControllerTest {

    @MockBean
    private FloorService floorService;

    @MockBean
    private FloorConverter floorConverter;

    @Test
    void save() throws Exception {

        FloorDTO floorDTO = FloorDTO.builder().number(1).build();
        String reqJson = objectMapper.writeValueAsString(floorDTO);

        when(floorConverter.toFloor(any(FloorDTO.class))).thenReturn(Floor.builder().build());
        when(floorService.save(any(Floor.class))).thenReturn(Floor.builder().build());
        when(floorConverter.toFloorDTO(any(Floor.class))).thenReturn(FloorDTO.builder().number(1).id(1L).build());

        mockMvc.perform(post("/floors")
                        .content(reqJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.number", is(1)));
    }

    @Test
    public void findById() throws Exception {

        when(floorService.findById(anyLong())).thenReturn(Floor.builder().build());
        when(floorConverter.toFloorDTO(any(Floor.class))).thenReturn(FloorDTO.builder().id(1L).build());

        mockMvc.perform(get("/floors/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.number", is(5)));
    }

    @Test
    public void deleteHappy() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/floors/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateHappy() throws Exception {

        FloorDTO floorDTO = FloorDTO.builder().number(2).id(1L).build();
        String reqJson = objectMapper.writeValueAsString(floorDTO);

        when(floorConverter.toFloorDTO(any())).thenReturn(floorDTO);

        mockMvc.perform(put("/floors/1")
                        .content(reqJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.number", is(2)));
    }
}