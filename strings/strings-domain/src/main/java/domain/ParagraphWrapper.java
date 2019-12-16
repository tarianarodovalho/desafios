package domain;

public class ParagraphWrapper {

    private ParagraphValidator paragraphValidator;

    public ParagraphWrapper(ParagraphValidator paragraphValidator){
        this.paragraphValidator = paragraphValidator;
    }

    public StringBuilder execute(String originalParagraph) {
        StringBuilder wrappedParagraph = new StringBuilder();
        StringBuilder wrappedLine = new StringBuilder();
        String[] words = originalParagraph.split(" ");

        for (int index = 0; index < words.length; index++) {
            String word = words[index];
            wrappedLine.append(word).append(" ");
            if (shouldStartNewLine(index, words, wrappedLine.length())) {
                wrappedLine.deleteCharAt(wrappedLine.length() - 1);
                wrappedParagraph.append(wrappedLine).append(System.lineSeparator());
                wrappedLine = new StringBuilder();
            }
        }
        return wrappedParagraph;
    }

    private boolean shouldStartNewLine(int index, String[] words, int wrappedLineSize){
        if(paragraphValidator.isLastWord(index, words.length)){
            return true;
        }
        String nextWord = words[index + 1];
        return paragraphValidator.nextWordExceedsLineLimit(nextWord.length(), wrappedLineSize);
    }
}
