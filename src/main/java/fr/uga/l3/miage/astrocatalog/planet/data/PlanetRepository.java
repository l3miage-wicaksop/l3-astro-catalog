package fr.uga.l3.miage.astrocatalog.planet.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {

    List<Planet> findByTypeAndHostName(Planet.Type type, String name);

    List<Planet> findByHostId(Long id);
}
