package com.aacademy.realestate.service.impl.func;

import com.aacademy.realestate.model.Floor;
import com.aacademy.realestate.repository.FloorRepository;
import com.aacademy.realestate.service.FloorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FloorServiceFuncTest {

    @Autowired
    private FloorService floorService;
    @Autowired
    private FloorRepository floorRepository;

    @Test
    public void verifyUpdate() {
        Floor floor = Floor.builder()
                .number(1)
                .build();

        Floor savedFloor = floorService.save(floor);
        Floor expected = Floor.builder()
                .number(2)
                .build();
        Floor actual = floorService.update(expected, savedFloor.getId());

        assertThat(expected.getId(), is(actual.getId()));
        assertThat(expected.getNumber(), is(actual.getNumber()));
    }

    @Test
    public void verifyFindAll() {
        floorRepository.saveAll(Arrays.asList(
                Floor.builder().number(1).build(),
                Floor.builder().number(2).build()));

        Set<Floor> actual = floorService.findAll();

        assertThat(actual.size(), is(2));
    }

    @Test
    public void verifySave() {
        Floor savedFloor = floorService.save(Floor.builder().number(1).build());
        Optional<Floor> actualFloor = floorRepository.findById(savedFloor.getId());
        assertTrue(actualFloor.isPresent());
    }

    @Test
    public void verifyDelete() {
        doNothing().when(floorRepository).deleteById(anyLong());

        floorService.delete(1L);
        verify(floorRepository, times(1));
    }

    @Test
    public void verifyDeleteById() {
        Floor savedFloor = floorRepository.save(Floor.builder().number(1).build());
        floorService.delete(savedFloor.getId());
        List<Floor> actual = floorRepository.findAll();
        assertThat(actual.size(), is(0));
    }

    @Test
    public void verifyByNumber() {
        Floor savedFloor = floorRepository.save(Floor.builder().number(1).build());
        Floor actual = floorService.findByNumber(1);
        assertThat(actual.getNumber(), is(savedFloor.getNumber()));
    }

    @Test
    public void verifyById() {
        Floor floor = Floor.builder()
                .number(1)
                .build();

        Floor expected = floorRepository.save(floor);
        Floor actual = floorService.findById(expected.getId());

        assertThat(expected, is(actual));
    }
}
