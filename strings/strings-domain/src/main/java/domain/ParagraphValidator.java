package domain;

public class ParagraphValidator {

    private Integer maximumCharactersPerLine;

    public ParagraphValidator(Integer maximumCharactersPerLine){
        this.maximumCharactersPerLine = maximumCharactersPerLine;
    }

    public int getMaximumCharactersPerLine() {
        return this.maximumCharactersPerLine;
    }

    boolean nextWordExceedsLineLimit(int nextWordSize, int wrappedLineSize){
        return wrappedLineSize + nextWordSize > maximumCharactersPerLine;
    }

    boolean isLastWord(int index, int wordsSize){
        return (index == wordsSize - 1);
    }
}
