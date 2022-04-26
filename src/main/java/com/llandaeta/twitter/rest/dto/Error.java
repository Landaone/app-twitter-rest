package com.llandaeta.twitter.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error{

    private final int httpCode;
    private final String message;

}
