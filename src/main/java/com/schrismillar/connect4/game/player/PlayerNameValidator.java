package com.schrismillar.connect4.game.player;

import org.springframework.stereotype.Component;

@Component
public class PlayerNameValidator {
    private static final String VALID_NAME_REGEX = "^[a-zA-Z0-9]+[a-zA-Z0-9 ]*$";

    public boolean isValid(String name) {
        //TODO - need to return some sort of validation results that includes valid/not and message why not valid
        return name.matches(VALID_NAME_REGEX) && name.length() <= 15;
    }
}
