package pl.filipwlodarczyk.mavenspring;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.Character.isLetter;

@Service
public class Encoder {
    public Response getSortedWord() {
        var csvText = getTextInCsvFormat();

        var listOfWords = mapToList(csvText);
        Collections.sort(listOfWords);

        var firstWord = listOfWords.get(0);
        return new Response(firstWord, encode(firstWord, 13));
    }

    private String encode(String text, int key) {
        return text.chars()
                .mapToObj(c -> {
                    char actualChar = (char) c;
                    return getEncodedCharacter(key, actualChar);
                })
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private StringBuilder getTextInCsvFormat() {
        var stringText = new StringBuilder();

        try {
            var text = readFile();
            while (text.hasNextLine()) {
                stringText.append(text.nextLine()).append(";");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return stringText;
    }

    private Character getEncodedCharacter(int key, char actualChar) {
        if (isLetter(actualChar)) {
            return encodeChar(key, actualChar);
        } else {
            return actualChar;
        }
    }

    private char encodeChar(int key, char actualChar) {
        return (char) ('a' + (actualChar - 'a' + key) % 26);
    }

    private List<String> mapToList(StringBuilder stringText) {
        return Arrays.asList(stringText.toString().split(";"));
    }

    private Scanner readFile() throws FileNotFoundException {
        var file = new File("src/main/java/pl/filipwlodarczyk/mavenspring/words.txt");
        return new Scanner(file);
    }
}
