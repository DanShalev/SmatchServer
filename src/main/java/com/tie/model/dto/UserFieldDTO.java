package com.tie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserFieldDTO {

    private String name;
    private Object data;
}
