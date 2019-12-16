package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphValidatorTest {

    /**
     * Verifies that the next word is within the limit of characters.
     */
    @Test
    void nextWordExceedsLineLimit_onLimit() {
        ParagraphValidator validator = new ParagraphValidator(40);

        boolean result = validator.nextWordExceedsLineLimit(5, 35);

        assertFalse(result);
    }

    /**
     * Verifies that the next word is over the limit of characters.
     */
    @Test
    void nextWordExceedsLineLimit_overLimit() {
        ParagraphValidator validator = new ParagraphValidator(40);

        boolean result = validator.nextWordExceedsLineLimit(6, 35);

        assertTrue(result);
    }

    /**
     * Verifies that the next word is under the limit of characters.
     */
    @Test
    void nextWordExceedsLineLimit_underLimit() {
        ParagraphValidator validator = new ParagraphValidator(40);

        boolean result = validator.nextWordExceedsLineLimit(4, 35);

        assertFalse(result);
    }

    /**
     * Verifies that the current word is not the last word.
     */
    @Test
    void isLastWord_itIsNotTheLastWord() {
        ParagraphValidator validator = new ParagraphValidator(40);

        boolean result = validator.isLastWord(5, 8);

        assertFalse(result);
    }

    /**
     * Verifies that the current word is the last word.
     */
    @Test
    void isLastWord_itIsTheLastWord() {
        ParagraphValidator validator = new ParagraphValidator(40);

        boolean result = validator.isLastWord(7, 8);

        assertTrue(result);
    }
}