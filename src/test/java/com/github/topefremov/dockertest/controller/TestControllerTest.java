package com.github.topefremov.dockertest.controller;

import com.github.topefremov.dockertest.domain.entity.User;
import com.github.topefremov.dockertest.domain.repository.TableRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TestController.class)
public class TestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TableRepository tableRepository;

    @Test
    public void getTest_shouldReturnValidResult() throws Exception {
        // given
        User user = new User();
        Long id = 1L;
        user.setId(id);
        user.setName("test");
        given(tableRepository.findById(id)).willReturn(Optional.of(user));

        // then
        mvc.perform(get("/api/test/"+id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(Integer.valueOf(user.getId().toString()))))
                .andExpect(jsonPath("name", is(user.getName())));

    }

    @Test
    public void getTest_shouldReturn404() throws Exception {
        // given
        Long id = 2L;
        given(tableRepository.findById(id)).willReturn(Optional.empty());

        // then
        mvc.perform(get("/api/test/"+id))
                .andExpect(status().isNotFound());
    }
 }