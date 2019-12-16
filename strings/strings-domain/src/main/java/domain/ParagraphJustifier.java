package domain;

import java.util.Collections;

public class ParagraphJustifier {

    private ParagraphValidator paragraphValidator;

    public ParagraphJustifier(ParagraphValidator paragraphValidator){
        this.paragraphValidator = paragraphValidator;
    }

    public StringBuilder execute(String wrappedParagraph) {
        String[] wrappedLines = extractLines(wrappedParagraph);
        StringBuilder justifiedParagraph = new StringBuilder();

        for(String line: wrappedLines){
            StringBuilder justifiedLine = justifyLine(line);
            justifiedParagraph.append(justifiedLine).append(System.lineSeparator());
        }
        return justifiedParagraph;
    }

    private String[] extractLines(String wrappedLine){
        return wrappedLine.split("\n");
    }

    private StringBuilder justifyLine(String line){
        String[] words = extractWordsFromLine(line);

        int totalRemainingSpaces = paragraphValidator.getMaximumCharactersPerLine() - countTotalNumberOfChars(words);
        int numberOfSpacesBetweenWords = totalRemainingSpaces;
        int numberOfExtraSpaces = 0;
        if(words.length > 1){
            numberOfSpacesBetweenWords = totalRemainingSpaces / (words.length - 1);
            numberOfExtraSpaces = totalRemainingSpaces % (words.length - 1);
        }

        StringBuilder justifiedLine = new StringBuilder();
        for (int index = 0; index < words.length; index++) {
            String word = words[index];
            if (paragraphValidator.isLastWord(index, words.length)) {
                justifiedLine.append(word);
            } else {
                String blankSpaces = createBlankSpaces(numberOfSpacesBetweenWords);
                StringBuilder wordWithSpaces = new StringBuilder();
                wordWithSpaces.append(word).append(blankSpaces);
                if (numberOfExtraSpaces > 0) {
                    numberOfExtraSpaces--;
                    wordWithSpaces.append(" ");
                }
                justifiedLine.append(wordWithSpaces);
            }
        }
        return justifiedLine;
    }

    private String[] extractWordsFromLine(String wrappedLine){
        return wrappedLine.split(" ");
    }

    private int countTotalNumberOfChars(String[] words) {
        int totalNumberOfChars = 0;
        for (String word : words) {
            totalNumberOfChars += word.length();
        }
        return totalNumberOfChars;
    }

    private String createBlankSpaces(int numberOfSpacesBetweenWords){
        return String.join("", Collections.nCopies(numberOfSpacesBetweenWords, " "));
    }
}