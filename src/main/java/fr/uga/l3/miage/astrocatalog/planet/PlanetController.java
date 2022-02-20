package fr.uga.l3.miage.astrocatalog.planet;

import fr.uga.l3.miage.astrocatalog.host.data.HostSystemService;
import fr.uga.l3.miage.astrocatalog.planet.data.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/hosts/{hostId}")
public class PlanetController {

    private final PlanetService planetService;
    private final PlanetMapper planetMapper;
    private final HostSystemService hostSystemService;

    @Autowired
    public PlanetController(PlanetService planetService, PlanetMapper planetMapper, HostSystemService hostSystemService) {
        this.planetService = planetService;
        this.planetMapper = planetMapper;
        this.hostSystemService = hostSystemService;
    }

    @PostMapping("/planets")
    public ResponseEntity<PlanetDTO> add(@PathVariable Long hostId, @RequestBody @Valid PlanetDTO planetDTO) throws URISyntaxException {

        var hostSystem = hostSystemService.findById(hostId);
        if (hostSystem.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        final var planet = planetMapper.toEntity(planetDTO);
        planet.setHost(hostSystem.get());
        planetService.save(planet);

        return ResponseEntity.created(new URI("/hosts/" + hostId + "/planets/" + planet.getId()))
                .body(planetMapper.toDTO(planet));

    }

    @GetMapping("/planets")
    public ResponseEntity<List<PlanetDTO>> getAll(@PathVariable Long hostId) throws URISyntaxException {

        var hostSystem = hostSystemService.findById(hostId);
        if (hostSystem.isPresent()) {
            return ResponseEntity.ok(planetMapper.toDTOs(planetService.findByHostId(hostId)));
        }
        return ResponseEntity.notFound().build();

    }

}
