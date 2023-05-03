package com.myblog.api.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PostCreate {

    // @NotBlank붙어 있는 경우는 값을 스프링에서 자동으로 검증해줌
    // controller에 넘어오기 전에 미리 검증으로 하고 400 error를 떨궈 주기때문에 브레이크 포인트에 걸리지 않음

    //notBlank(message = "")를 통해서 에러가 났을 때 메세지를 지정할 수 있음
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
}
