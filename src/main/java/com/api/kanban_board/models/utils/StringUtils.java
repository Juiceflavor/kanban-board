package com.api.kanban_board.models.utils;

import com.api.kanban_board.exceptions.ConflictException;

public final class StringUtils {

    private StringUtils() {}

    public static void validateNullAndEmpty(String name, String value){
        if(name == null || name.isEmpty()){
            validateNullAndEmpty(value);
        }

        if(value == null || value.isEmpty()){
            throw new ConflictException(String.format("%s is null or empty", name));
        }
    }

    private static void validateNullAndEmpty(String value){
        if(value == null || value.isEmpty()){
            throw new ConflictException("value is null or empty");
        }
    }
}
