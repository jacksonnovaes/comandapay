package com.codexmind.establishment.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {

    private static final long serialVersionUID = 1L;
    private Integer status;
    private String msg;
    private Long timaStamp;
    private String uri;

}
