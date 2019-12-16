package usecase;

import domain.ParagraphJustifier;
import domain.ParagraphWrapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class TextFormatterTest {

    @Test
    void execute() {
        Stream<String> inputText = Stream.of("In the beginning God created the heavens and the earth. Now the earth was " +
                "formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering " +
                "over the waters. \n\nAnd God said, 'Let there be light', and there was light. God saw that the " +
                "light was good, and he separated the light from the darkness. God called the light 'day', and the " +
                "darkness he called 'night'. And there was evening, and there was morning - the first day.");
        String wrappedParagraph = "In the beginning God created the heavens\n" +
                "and the earth. Now the earth was\n" +
                "formless and empty,  darkness was over\n" +
                "the surface of the deep, and the Spirit\n" +
                "of God was hovering over the waters.\n" +
                "\n\n" +
                "And God said, 'Let there be light',\n" +
                "and there was light. God saw that the\n" +
                "light was good, and he separated the\n" +
                "light from the darkness. God called the\n" +
                "light 'day', and the darkness he called\n" +
                "'night'. And there was evening, and\n" +
                "there was morning - the first day.";
        List<String> expectedText = new ArrayList<>();
        expectedText.add("In the beginning God created the heavens\n" +
                        "and   the   earth.  Now  the  earth  was\n" +
                        "formless  and  empty,  darkness was over\n" +
                        "the  surface of the deep, and the Spirit\n" +
                        "of  God  was  hovering  over the waters.\n" +
                        "\n\n" +
                        "And  God  said,  'Let  there  be light',\n" +
                        "and  there  was  light. God saw that the\n" +
                        "light  was  good,  and  he separated the\n" +
                        "light  from the darkness. God called the\n" +
                        "light  'day', and the darkness he called\n" +
                        "'night'.  And  there  was  evening,  and\n" +
                        "there  was  morning  -  the  first  day.");
        ParagraphWrapper paragraphWrapper = mock(ParagraphWrapper.class);
        ParagraphJustifier paragraphJustifier = mock(ParagraphJustifier.class);
        when(paragraphWrapper.execute(anyString())).thenReturn(new StringBuilder(wrappedParagraph));
        when(paragraphJustifier.execute(wrappedParagraph)).thenReturn(new StringBuilder(expectedText.get(0)));
        TextFormatter textFormatter = new TextFormatter(paragraphWrapper, paragraphJustifier);

        List<String> result = textFormatter.execute(inputText);

        assertEquals(result.toArray().length, 1);
        assertArrayEquals(result.toArray(), expectedText.toArray());
    }
}