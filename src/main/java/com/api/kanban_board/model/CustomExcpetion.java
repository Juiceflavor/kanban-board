package com.api.kanban_board.model;

public class CustomExcpetion extends Exception {

    public CustomExcpetion(String field, String action) {
        super(message(field, action));
    }

    private static String message(String field, String action) {
        String cadena = "";
        switch (action) {
            case "Transition" -> cadena = String.format("The %s has finished", field);
            case "Validate" -> cadena = String.format("The field %s is required", field);
            case "Inactive", "Active" -> cadena = String.format("The %s has inactive", field);
            default -> cadena = ("An unexpected error occurred during " + action);
        }

        return cadena;
    }
}
