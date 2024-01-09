package pl.filipwlodarczyk.mavenspring;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

@Service
public class CezarService {
    public String getSortedWord() {
        var stringText = new StringBuilder();

        try {
            var text = readFile();
            while (text.hasNextLine()) {
                stringText.append(text.nextLine()).append(";");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        var listOfWords = Arrays.asList(stringText.toString().split(";"));
        Collections.sort(listOfWords);

        return encode(listOfWords.get(0), 13);
    }

    public String encode(String text, int key) {
        var encodedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            var actualChar = text.charAt(i);

            if (Character.isLetter(actualChar)) {
                var encodedChar = (char) ('a' + (actualChar - 'a' + key) % 26);
                encodedText.append(encodedChar);
            } else {
                encodedText.append(actualChar);
            }
        }

        return encodedText.toString();
    }

    private Scanner readFile() throws FileNotFoundException {
        var file = new File("src/main/java/pl/filipwlodarczyk/mavenspring/words.txt");
        return new Scanner(file);
    }
}
