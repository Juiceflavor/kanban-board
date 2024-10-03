package com.api.kanban_board.models.utils;

import com.api.kanban_board.exceptions.ConflictException;

public final class IntegerUtils {

    private IntegerUtils() {}

    public static void validateNullAndNegative (String name, Integer value){
        if(name == null || name.isEmpty()){
            validateNullAndNegative(value);
        }

        if (value == null || value <= 0){
            throw new ConflictException(String.format("%s is null or negative", name));
        }
    }

    private static void validateNullAndNegative (Integer value){
        if (value == null || value <= 0){
            throw new ConflictException("Value is null or negative");
        }
    }
}
