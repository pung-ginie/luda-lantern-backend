package com.genie.ludalantern.dto;

import lombok.*;

import java.util.List;

//HTTP 응답으로 사용할 DTO
// 리스트 형식으로 받아온다
//캡슐화를 위해서
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseDTO<T> {
    private String error;
    private List<T> data;
    private T data2;
    private String message;


}
