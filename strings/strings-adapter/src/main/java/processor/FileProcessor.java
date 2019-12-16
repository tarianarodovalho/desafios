package processor;

import usecase.TextFormatter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileProcessor {

    private TextFormatter textFormatter;

    public FileProcessor(TextFormatter textFormatter){
        this.textFormatter = textFormatter;
    }

    public void execute(String inputFilePath, String outputFilePath){
        File resourcesDirectory = new File(inputFilePath);
        Path path = Paths.get(resourcesDirectory.getAbsolutePath());
        try (Stream<String> inputText = Files.lines(path)) {
            List<String> formattedText = textFormatter.execute(inputText);
            write(formattedText, outputFilePath);
        } catch (IOException e){
            System.out.println("Não foi possível ler o arquivo. O caminho está correto?");
        }
    }

   private void write(List<String> formattedText, String outputPath) {
        try{
            FileWriter writer = new FileWriter(outputPath);
            for(String line: formattedText) {
                writer.write(line);
            }
            writer.close();
        } catch (IOException e){
            System.out.println("Não foi possível escrever no arquivo.");
        }
    }
}
