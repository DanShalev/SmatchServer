package com.tie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class GroupDto {

    private String id;
    private String name;
    private String description;
    private String numberOfMembers;
    private String avatarUrl;
    private Map<Integer, String> fields;

}
