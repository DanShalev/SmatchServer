package com.tie.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class GroupDto {

    public GroupDto(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    private String id;
    private String name;
    private String description;
    private List<String> fields;


}
