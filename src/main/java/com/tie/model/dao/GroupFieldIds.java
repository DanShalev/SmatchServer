package com.tie.model.dao;

import lombok.Data;
import java.io.Serializable;

@Data
public class GroupFieldIds implements Serializable {

    private String groupId;
    private String fieldName;
}
