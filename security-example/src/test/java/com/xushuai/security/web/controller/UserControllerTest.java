package com.xushuai.security.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getUserTest() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1") // 发送get请求，对应的有post,put,delete等
                .contentType(MediaType.APPLICATION_JSON_UTF8)) // 设置contentType
                .andExpect(MockMvcResultMatchers.status().isOk()) // 校验返回的状态码是否为200
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("baker-1"))
                .andReturn().getResponse().getContentAsString();// 校验返回的json对象中的username属性是否为baker-1

        System.out.println(result);
    }

    @Test
    public void getUsernameTest() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/username/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("baker-2"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void addUserTest() throws Exception {
        //language=JSON
        String param = "{\"username\":\"baker-3\",\"password\":null}";

        String result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(param))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

}