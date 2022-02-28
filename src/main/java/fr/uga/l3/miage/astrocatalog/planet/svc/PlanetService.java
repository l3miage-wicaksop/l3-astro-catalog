package fr.uga.l3.miage.astrocatalog.planet.svc;

import fr.uga.l3.miage.astrocatalog.planet.data.Planet;
import fr.uga.l3.miage.astrocatalog.planet.data.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PlanetService {

    private final PlanetRepository repository;

    @Autowired
    public PlanetService(PlanetRepository repository) {
        this.repository = repository;
    }

    public Planet findById(Long id) {
        return repository.getById(id);
    }


    public List<Planet> findByHostId(Long id) {
        return repository.findByHostId(id);
    }

    public List<Planet> findByTypeAndHostName(Planet.Type type, String name) {
        return repository.findByTypeAndHostName(type, name);
    }

    public void save(Planet planet) {
        repository.save(planet);
    }
}
