package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphWrapperTest {

    /**
     * Verifies that the wrapped text has less than 40 characters comparing it with the expected text.
     */
    @Test
    void execute() {
        String originalText = "In the beginning God created the heavens and the earth. Now the earth was formless " +
                "and empty,  darkness was over the surface of the deep, and the Spirit of God was hovering over the " +
                "waters.";
        String expectedText = "In the beginning God created the heavens\n" +
                "and the earth. Now the earth was\n" +
                "formless and empty,  darkness was over\n" +
                "the surface of the deep, and the Spirit\n" +
                "of God was hovering over the waters.\n";
        ParagraphWrapper paragraphWrapper = new ParagraphWrapper(new ParagraphValidator(40));

        StringBuilder result = paragraphWrapper.execute(originalText);

        assertEquals(expectedText, result.toString());
    }
}