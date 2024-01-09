package pl.filipwlodarczyk.mavenspring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class CezarController {
    private final CezarService cezarService;

    public CezarController(CezarService cezarService) {
        this.cezarService = cezarService;
    }

    @GetMapping
    public Response getCezar() {
        return cezarService.getSortedWord();
    }
}
