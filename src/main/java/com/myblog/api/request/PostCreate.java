package com.myblog.api.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
// @AllArgsConstructor
public class PostCreate {

    // @NotBlank붙어 있는 경우는 값을 스프링에서 자동으로 검증해줌
    // controller에 넘어오기 전에 미리 검증으로 하고 400 error를 떨궈 주기때문에 브레이크 포인트에 걸리지 않음

    //notBlank(message = "")를 통해서 에러가 났을 때 메세지를 지정할 수 있음
    @NotBlank(message = "제목을 입력해주세요.")
    private final String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private final String content;

    // @Builder 알아보기
    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //빌더의 장점
    // - 가독성이 좋다.
    // - 값생성의 대한 유연함
    // - 필요한 값만 받을 수 있다. -> 오버로딩 가능한 조건의 대해서도 찾아보기
    // - 객체의 불변성
    public static PostCreate changeTitle(String title, String content){
        return PostCreate.builder()
            .title(title)
            .content(content)
            .build();
    }
}
