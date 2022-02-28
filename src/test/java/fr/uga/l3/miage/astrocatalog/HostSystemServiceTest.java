package fr.uga.l3.miage.astrocatalog;

import fr.uga.l3.miage.astrocatalog.host.data.HostSystem;
import fr.uga.l3.miage.astrocatalog.host.svc.HostSystemService;
import fr.uga.l3.miage.astrocatalog.planet.data.Planet;
import fr.uga.l3.miage.astrocatalog.planet.svc.PlanetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class HostSystemServiceTest {

    @Autowired
    private HostSystemService hostSystemService;

    @Autowired
    private PlanetService planetService;

    @Test
    void shouldSaveSystem() throws Exception {

        final var solar_system = new HostSystem()
                .setNumberOfStar(1);
        solar_system
                .setMass(1)
                .setDistance(.0)
                .setName("Solar System");


        var jupiter = new Planet()
                .setType(Planet.Type.GAS_GIANT)
                .setConfirmed(true)
                .setDiscovery(Date.from(LocalDateTime.now().minus(2000, ChronoUnit.YEARS).toInstant(ZoneOffset.UTC)))
                .setOrbitalPeriod(21)
                .setSemiMajorAxis(5);
        jupiter.setMass(1)
                .setDistance(5.)
                .setName("Jupiter");

        var mars = new Planet()
                .setType(Planet.Type.EARTH_LIKE)
                .setConfirmed(true)
                .setDiscovery(Date.from(LocalDateTime.now().minus(2000, ChronoUnit.YEARS).toInstant(ZoneOffset.UTC)))
                .setOrbitalPeriod(2)
                .setSemiMajorAxis(2);
        mars.setMass(1)
                .setDistance(2.)
                .setName("Mars");

        hostSystemService.save(solar_system, List.of(mars, jupiter));

        final var sun = hostSystemService.findById(solar_system.getId());
        assertThat(sun).isPresent();
        assertThat(sun.get()).isNotSameAs(solar_system);

        final var ourPlanets = planetService.findByHostId(sun.get().getId());
        assertThat(ourPlanets).hasSize(2);

    }

}
