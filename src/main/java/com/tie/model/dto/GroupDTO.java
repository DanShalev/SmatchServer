package com.tie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class GroupDTO {

    private String id;
    private String name;
    private String description;
    private String numberOfMembers;
    private String avatar;
    private String category;
    private Map<Integer, String> fields;

}
