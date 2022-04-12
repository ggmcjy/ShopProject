package com.example.torderproject.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLoginApi {
    private int code = HttpStatus.OK.value();
    private Object result;
}


