package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphJustifierTest {

    /**
     * Verifies that each justified line has the limit of 40 characters, including the blank spaces, comparing it with a justified line.
     */
    @Test
    void execute() {
        String wrappedParagraph = "In the beginning God created the heavens\n" +
                "and the earth. Now the earth was\n" +
                "formless and empty,  darkness was over\n" +
                "the surface of the deep, and the Spirit\n" +
                "of God was hovering over the waters.\n";
        ParagraphJustifier paragraphJustifier = new ParagraphJustifier(new ParagraphValidator(40));

        StringBuilder result = paragraphJustifier.execute(wrappedParagraph);

        assertEquals("In the beginning God created the heavens", result.substring(0, 40));
        assertEquals("and   the   earth.  Now  the  earth  was", result.substring(41, 81));
        assertEquals("formless  and  empty,  darkness was over", result.substring(82, 122));
        assertEquals("the  surface of the deep, and the Spirit", result.substring(123, 163));
        assertEquals("of  God  was  hovering  over the waters.", result.substring(164, 204));
    }
}