package fr.uga.l3.miage.astrocatalog;

import fr.uga.l3.miage.astrocatalog.host.data.HostSystem;
import fr.uga.l3.miage.astrocatalog.host.data.HostSystemRepository;
import fr.uga.l3.miage.astrocatalog.planet.data.Planet;
import fr.uga.l3.miage.astrocatalog.planet.data.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PlanetRepositoryTest {

    @Autowired
    HostSystemRepository hostSystemRepository;

    @Autowired
    PlanetRepository planetRepository;

    @Test
    void shouldGetPlanets() throws Exception {

        final var solar_system = new HostSystem()
                .setNumberOfStar(1);
        solar_system
                .setDistance(.0)
                .setName("Solar System");

        hostSystemRepository.save(solar_system);

        var jupiter = new Planet()
                .setHost(solar_system)
                .setType(Planet.Type.GAS_GIANT)
                .setConfirmed(true)
                .setDiscovery(Date.from(LocalDateTime.now().minus(2000, ChronoUnit.YEARS).toInstant(ZoneOffset.UTC)))
                .setOrbitalPeriod(21)
                .setSemiMajorAxis(5);

        jupiter.setMass(1)
                .setDistance(.5)
                .setName("Jupiter");


        var mars = new Planet()
                .setHost(solar_system)
                .setType(Planet.Type.EARTH_LIKE)
                .setConfirmed(true)
                .setDiscovery(Date.from(LocalDateTime.now().minus(2000, ChronoUnit.YEARS).toInstant(ZoneOffset.UTC)))
                .setOrbitalPeriod(2)
                .setSemiMajorAxis(2);

        mars.setMass(1)
                .setDistance(.2)
                .setName("Mars");

        planetRepository.save(jupiter);
        planetRepository.save(mars);

        solar_system.setPlanets(new ArrayList<>(List.of(mars, jupiter)));
        hostSystemRepository.save(solar_system);

        var ourGasGiants = planetRepository.findByTypeAndHostName(Planet.Type.GAS_GIANT, "Solar System");
        assertThat(ourGasGiants).hasSize(1).extracting(Body::getName).containsExactly("Jupiter");

    }

}
