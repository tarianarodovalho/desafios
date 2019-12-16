package usecase;

import domain.ParagraphJustifier;
import domain.ParagraphWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TextFormatter {

    private ParagraphWrapper paragraphWrapper;
    private ParagraphJustifier paragraphJustifier;

    public TextFormatter(ParagraphWrapper paragraphWrapper, ParagraphJustifier paragraphJustifier) {
        this.paragraphWrapper = paragraphWrapper;
        this.paragraphJustifier = paragraphJustifier;
    }

    public ArrayList<String> execute(Stream<String> inputText) {
        return inputText.collect(ArrayList::new, this::formatText, List::addAll);
    }

    private void formatText(ArrayList<String> formattedText, String originalParagraph) {
        StringBuilder wrappedParagraph = paragraphWrapper.execute(originalParagraph);
        StringBuilder justifiedParagraph = paragraphJustifier.execute(wrappedParagraph.toString());
        formattedText.add(justifiedParagraph.toString());
    }
}