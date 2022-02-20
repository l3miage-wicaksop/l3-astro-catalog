package fr.uga.l3.miage.astrocatalog.host;

import fr.uga.l3.miage.astrocatalog.host.data.HostSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/hosts")
public class HostSystemController {

    private final HostSystemService service;
    private final HostSystemMapper mapper;

    @Autowired
    public HostSystemController(HostSystemService service, HostSystemMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<HostSystemDTO> create(@RequestBody @Valid HostSystemDTO dto) throws URISyntaxException {
        final var hostSystem = mapper.toEntity(dto);
        service.save(hostSystem);
        return ResponseEntity.created(new URI("/hosts/" + hostSystem.getId())).body(mapper.toDTO(hostSystem));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HostSystemDTO> get(@PathVariable Long id) {
        final var hostSystem = service.findById(id);
        if (hostSystem.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.toDTO(hostSystem.get()));
    }

    @GetMapping
    public ResponseEntity<List<HostSystemDTO>> get(@RequestParam(required = false) Integer numberOfStar) {
        if (numberOfStar != null && numberOfStar > 0) {
            return ResponseEntity.ok(mapper.toDTOs(service.findHostByNumberOfStar(numberOfStar)));
        }
        return ResponseEntity.ok(mapper.toDTOs(service.findAll()));
    }
}
