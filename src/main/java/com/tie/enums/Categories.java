package com.tie.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Categories {
    TRAVEL("Travel"),
    SPORT("Sport"),
    DATING("Dating"),
    GAMES("Games"),
    EDUCATION("Education"),
    OTHER("Other");

    @Getter
    private static final List<String> values;

    static {
        values = Arrays.stream(Categories.values()).map(Categories::getValue).collect(Collectors.toList());
    }

    private final String value;

}

