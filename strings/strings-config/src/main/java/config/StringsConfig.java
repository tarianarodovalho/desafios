package config;

import domain.ParagraphValidator;
import domain.ParagraphJustifier;
import domain.ParagraphWrapper;
import processor.FileProcessor;
import usecase.TextFormatter;

import java.io.File;

public class StringsConfig {

    private Integer MAXIMUM_CHARACTERS_PER_LINE = 40;

    private final String outputPath = System.getProperty("user.home") + File.separator + "formattedText.txt";

    private final ParagraphValidator paragraphValidator = new ParagraphValidator(MAXIMUM_CHARACTERS_PER_LINE);
    private final ParagraphWrapper paragraphWrapper = new ParagraphWrapper(paragraphValidator);
    private final ParagraphJustifier paragraphJustifier = new ParagraphJustifier(paragraphValidator);
    private final TextFormatter textFormatter = new TextFormatter(paragraphWrapper, paragraphJustifier);

    public StringsConfig(String maximumCharactersUserInput) {
        if (maximumCharactersUserInput != null && !maximumCharactersUserInput.isEmpty()){
            MAXIMUM_CHARACTERS_PER_LINE = Integer.valueOf(maximumCharactersUserInput);
        }
    }

    public FileProcessor fileProcessor() {
        return new FileProcessor(textFormatter);
    }

    public String getOutputPath(){
        return this.outputPath;
    }

}
