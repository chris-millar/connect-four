package com.schrismillar.connect4.game.player;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerNameValidatorTest {

    private PlayerNameValidator playerNameValidator;

    @Before
    public void setUp() {
        playerNameValidator = new PlayerNameValidator();
    }

    @Test
    public void isValidReturnsTrueForValidName() {
        assertTrue("Should be a valid name", playerNameValidator.isValid("abcXYZ123"));
    }

    @Test
    public void isValidReturnsTrueWhenNameContainsSpaceAfterAtLeastOneAlphanumeric() {
        assertTrue("Should be an valid name even with space as long as started with alphanumeric",
                    playerNameValidator.isValid("abc 123"));
    }

    @Test
    public void isValidReturnsTrueWhenNameContainsSingleValidCharacter() {
        assertTrue("Should be an valid name even with single valid character",
                playerNameValidator.isValid("a"));
    }

    @Test
    public void isValidReturnsFalseWhenNameStartsWithSpace() {
        assertFalse("Should be an invalid name because it starts with space character",
                playerNameValidator.isValid(" abc"));
    }

    @Test
    public void isValidReturnsFalseWhenNameContainsNonAlphaNumericCharacters() {
        assertFalse("Should be an invalid name because it contains non alphanumeric characters",
                    playerNameValidator.isValid("$@^#&$"));
    }


    @Test
    public void isValidReturnsFalseWhenNameContainsBreak() {
        assertFalse("Should be an invalid name because it contains a line break",
                    playerNameValidator.isValid("abc\n123"));
    }

    @Test
    public void isValidReturnsFalseWhenNameLengthIsGreaterThan15() {
        String validCharactersButLength16 = "abcdefghijklmonp";
        assertFalse("Should be an invalid name because it is greater than 15 characters long",
                    playerNameValidator.isValid(validCharactersButLength16));
    }
}