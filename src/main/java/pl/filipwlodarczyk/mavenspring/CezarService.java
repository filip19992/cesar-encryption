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
public class CezarService {
    private final Encoder encoder;

    public CezarService(Encoder encoder) {
        this.encoder = encoder;
    }

    public Response getSortedWord() {
        return encoder.getSortedWord();
    }
}
