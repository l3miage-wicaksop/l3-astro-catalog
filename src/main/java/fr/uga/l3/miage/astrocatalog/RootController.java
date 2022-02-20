package fr.uga.l3.miage.astrocatalog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping(value = "/")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("OK");
    }

}
