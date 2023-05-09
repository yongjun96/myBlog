package com.myblog.api.controller;

import com.jayway.jsonpath.JsonPath;
import com.myblog.api.domain.Post;
import com.myblog.api.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// @WebMvcTest
@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;


    /** 중요★ : 항상 해당 메소드가 수행이 되도록 보장해주는 어노테이션 */
    @BeforeEach
    void clean(){
        postRepository.deleteAll();
    }

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
            .andExpect(MockMvcResultMatchers.content().string("{}"))
            .andDo(MockMvcResultHandlers.print());

        // db -> post하나 등록됨
    }


    @Test
    @DisplayName("/posts 요청시 tilte값은 필수")
    void test2() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": null, \"content\" : \"내용입니다.\"}")
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            // junit5 jsonPath 찾아보기
            .andExpect(MockMvcResultMatchers.jsonPath("$.validation.title").value("제목을 입력해주세요."))
            .andDo(MockMvcResultHandlers.print());

    }


    @Test
    @DisplayName("/posts 요청시 tilte값은 필수")
    void test3() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": null, \"content\" : null}")
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("잘못된 요청입니다."))
            .andExpect(MockMvcResultMatchers.jsonPath("$.validation.title").value("제목을 입력해주세요."))
            .andExpect(MockMvcResultMatchers.jsonPath("$.validation.content").value("내용을 입력해주세요."))
            .andDo(MockMvcResultHandlers.print());
    }



    @Test
    @DisplayName("/posts 요청시 DB에 값이 저장된다.")
    void test4() throws Exception {
        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"제목입니다.\", \"content\" : \"내용입니다.\"}")
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
        //then

        // expected : 1L -> 11개의 값이 있을거라고 예상 postRepository.count() 와 일치해야 함

        // 전체 테스트의 경우 성공의 경우가 2번이기때문에 2L
        Assertions.assertEquals(1L, postRepository.count());

        Post post = postRepository.findAll().get(0);
        Assertions.assertEquals("제목입니다.", post.getTitle());
        Assertions.assertEquals("내용입니다.", post.getContent());

    }

}