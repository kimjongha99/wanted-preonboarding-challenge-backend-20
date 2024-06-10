package com.example.wantedchallenge.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

public class ResponseDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Error {
        private HttpStatus httpStatus;
        private String message;



}
}
