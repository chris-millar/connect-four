package com.schrismillar.connect4.game.player;

public class PlayerNameValidator {
    private static final String VALID_NAME_REGEX = "^[a-zA-Z0-9_]+$";

    boolean isValid(String name) {
        return name.matches(VALID_NAME_REGEX) && name.length() <= 15;
    }
}
