package fr.uga.l3.miage.astrocatalog.host.svc;

import fr.uga.l3.miage.astrocatalog.host.data.HostSystem;
import fr.uga.l3.miage.astrocatalog.host.data.HostSystemRepository;
import fr.uga.l3.miage.astrocatalog.planet.data.Planet;
import fr.uga.l3.miage.astrocatalog.planet.svc.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HostSystemService {

    private final HostSystemRepository repository;
    private final PlanetService planetService;

    @Autowired
    public HostSystemService(HostSystemRepository repository, PlanetService planetService) {
        this.repository = repository;
        this.planetService = planetService;
    }

    public Optional<HostSystem> findById(Long id) {
        return repository.findById(id);
    }

    public List<HostSystem> findHostByNumberOfStar(int numberOfStar) {
        return repository.findAllByNumberOfStar(numberOfStar);
    }

    public HostSystem save(HostSystem host) {
        return repository.save(host);
    }

    // for tests only
    public void save(HostSystem host, List<Planet> planets) {
        save(host);
        host.setPlanets(new ArrayList<>());
        for (Planet planet : planets) {
            planet.setHost(host);
            host.getPlanets().add(planet);
            planetService.save(planet);
        }
    }

    public List<HostSystem> findAll() {
        return repository.findAll();
    }

}
