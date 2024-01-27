package pl.filipwlodarczyk.mavenspring;

import org.springframework.stereotype.Service;

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
