package com.study.restdocs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SomethingResponse {

    private final Long number;
    private final String text;
}
