package application;

import config.StringsConfig;
import processor.FileProcessor;

public class Main {

    /**
     * Create a justified text file from args[0] limited to args[1] characters per line into user home directory.
     * Being:
     * args[0] - path to original text file
     * args[1] - number of characters per line
     * @param args
     */
    public static void main(String[] args) {
        String inputFilePath = args[0];
        String maximumCharactersUserInput = args[1];

        StringsConfig config = new StringsConfig(maximumCharactersUserInput);
        FileProcessor fileProcessor = config.fileProcessor();
        fileProcessor.execute(inputFilePath, config.getOutputPath());

        System.out.println("Texto formatado com sucesso e disponibilizado em " + config.getOutputPath());
    }
}
