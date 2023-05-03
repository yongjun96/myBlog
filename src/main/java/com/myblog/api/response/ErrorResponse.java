package com.myblog.api.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * {
 *     "code": "400",
 *     "message": "잘못된 요청입니다.",
 *     "validation": {
 *         "title": "값을 입력해주세요."
 *     }
 * }
 *
 */

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {

    //케바케임
    private final String code;
    private final String message;

}
