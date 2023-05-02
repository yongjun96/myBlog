package com.myblog.api.controller;

import com.myblog.api.request.PostCreate;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PostController {


    /**
     알아볼 것
     @Slf4j
     @RestController
     */

    /**
     주요방식

     SSR -> jsp, thymeleaf, mustache, freemarker
     -> html rendering
     SPA
     vue -> vue+SSR = nuxt.js
     -> javascript <-> API (JSON)

     react -> react+SSR = next.js


     Http Method
     GET, POST, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT
     // 글등록 -> POST Method
     */




    /** 방식 1 */
    @PostMapping("/posts1")
    public String post1(@RequestParam String title, @RequestParam String content) {
        log.info("title={}, content={}", title, content);
        return "Hello World";
    }


    /** 방식 2 */
    @PostMapping("/posts2")
    public String post2(@RequestParam Map<String, String> params) {
        log.info("params={}", params);
        String title = params.get("title");
        String content = params.get("content");
        log.info("title= {}, content = {}", title, content);
        return "Hello World";
    }


    /** 방식 3 */
    @PostMapping("/posts")
    public String post(@ModelAttribute PostCreate params) {
        log.info("params = {}", params.toString());
        return "Hello World";
    }
}
