package com.tie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GroupDto {

    private String id;
    private String name;
    private String description;
    private List<String> fields;


}
