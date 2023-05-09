package com.myblog.api.controller;

import com.myblog.api.request.PostCreate;
import com.myblog.api.service.PostService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
// @RestController -> @Controller + @ResponseBody
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


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
    @PostMapping("/posts3")
    public String post3(@ModelAttribute PostCreate params) {
        log.info("params = {}", params.toString());
        return "Hello World";
    }


    /** 방식 4 */
    @PostMapping("/posts4")
    public Map<String, String> post4(@RequestBody @Valid PostCreate params, BindingResult result) throws Exception {

        // 데이터 검증이유
        // 1. client 개발자가 실수로 값을 보내지 않을 경우
        // 2. client bug로 값이 누락될 수 있음.
        // 3. 외부에 나쁜 사람이 값을 임의로 조작할 수 있음
        // 4. DB에 값을 저장할 때 의도치 않은 오류 발생
        // 5. 서버 개발자의 편안함을 위해서

        /*
        String title = params.getTitle();
        if(title == null || title.equals("")){
            // 1. 힘들다 (노가다)
            // 2. 3번이상 반복작업을 하면 잘못 된 것이 있는지 의심한다
            // 3. 누락가능성 높음 (버그발생)
            // 4. 생각보다 검증해야 할 것이 많다.
            // 5. 개발자 스럽지 않다


            throw new Exception("타이틀값이 없어요!");
        }
        */

        log.info("params = {}", params.toString());
        // {"title": "타이틀 값이 없습니다."}

        if(result.hasErrors()){
            //hasErrors 메서드를 이용해서 필드에러스의 값을 필드에러 리스트에 담는다 확인
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);

            // @NotBlank의 걸려있는 변수 자체가 getField에 들어가게 됨
            String fieldName = firstFieldError.getField();

            // @NotBlank의 걸려있는 변수에 에러메세지가 getDefaultMessage로 표현됨
            String errorMessage = firstFieldError.getDefaultMessage();

            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);

            // 리턴을 일단 맵으로 해준 후,
            return error;
        }

        // java9 부터 사용가능 / 맵 데이터를 초기화
        // .of() -> 개수가 10개 제한
        // .ofEntries() -> 개수가 11개 이상인 경우
        // 주의점 : 초기화가 되고 난 이후 put이나 remove를 통해 객체의 데이터 변경 불가
        return Map.of();
    }


    /** 방식 5 */
    @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostCreate request) throws Exception {

        // repository.save(params);
        // db.save(params);

        postService.write(request);

        return Map.of();
    }

}

