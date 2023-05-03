package com.myblog.api.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/posts 요청시 Hello World를 출력한다.")
    void test() throws Exception {
        // 글 제목
        // 글 내용
        // 사용자
        // id
        // name
        // level

        /**
         * json
         * {
         *      "tilte" : "xxx",
         *      "content" : "xxx",
         *      "user" : {
         *                 "id" : "xxx",
         *                 "name" : "xxx"
         *               }
         * }
         */



        //mockMvc의 Content-Type[보내거나 받을 때 Http의 헤더값]

        // expected
        /*mockMvc.perform(MockMvcRequestBuilders.post("/posts")    // application/json
                                              .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                              .param("title", "글 제목")
                                              .param("content", "글 내용입니다 하하")
                )
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().string("Hello World"))
               .andDo(MockMvcResultHandlers.print());*/

        // MockMvcResultHandlers.print() -> http요청에 대한 서머리를 남겨줌


        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"제목입니다.\", \"content\" : \"내용입니다.\"}")
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Hello World"))
            .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @DisplayName("/posts 요청시 tilte값은 필수")
    void test2() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": null, \"content\" : \"내용입니다.\"}")
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
            // junit5 jsonPath 찾아보기
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("제목을 입력해주세요."))
            .andDo(MockMvcResultHandlers.print());

    }


}