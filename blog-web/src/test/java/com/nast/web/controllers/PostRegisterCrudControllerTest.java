package com.nast.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nast.domain.dto.PostRegisterDto;
import com.nast.domain.dto.mapper.PostMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.Random;

import static com.nast.domain.utils.DomainEntityBuilder.buildRandomString;
import static com.nast.domain.utils.DomainEntityBuilder.getPostRegisterBuilder;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:test-web-context.xml")
public class PostRegisterCrudControllerTest {

    private static final String urlPrefix = "postRegister";

    private static final Random rand = new Random(System.nanoTime());

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper jsonMapper;

    @Autowired
    private PostMapper postMapper;

    @Before
    public void setup() {

    }

    private PostRegisterDto buildRandomDto() {
        return getPostRegisterBuilder().dto()
                .general(rand.nextLong(), rand.nextLong(), rand.nextLong())
                .info(buildRandomString(), LocalDateTime.now())
                .meta(buildRandomString())
                .buildDto();
    }


    @Test
    public void test_assertMvcNotNull_Success() {
        assertNotNull(mockMvc);
    }


    @Test
    public void test_save_Success() throws Exception {
        PostRegisterDto randDtoForSave = buildRandomDto();
        String requestJson = jsonMapper.writeValueAsString(randDtoForSave);

        MvcResult saveResponse = mockMvc.perform(post(urlPrefix)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();
    }
}