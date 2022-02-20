package fr.uga.l3.miage.astrocatalog;

import fr.uga.l3.miage.astrocatalog.host.data.HostSystem;
import fr.uga.l3.miage.astrocatalog.host.data.HostSystemRepository;
import fr.uga.l3.miage.astrocatalog.planet.data.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class HostSystemRepositoryTest {

    @Autowired
    HostSystemRepository hostSystemRepository;

    @Autowired
    PlanetRepository planetRepository;

    @Autowired
    EntityManager entityManager;


    @Test
    void shouldSaveHost() throws Exception {

        final var solar_system = new HostSystem()
                .setNumberOfStar(1);
        solar_system
                .setDistance(.0)
                .setMass(1)
                .setName("Solar System");

        hostSystemRepository.save(solar_system);
        assertThat(solar_system.getId()).isNotNull();
        entityManager.detach(solar_system);
        var sun = hostSystemRepository.getById(solar_system.getId());
        assertThat(sun).isNotSameAs(solar_system);
    }

    @Test
    void shouldGetHostByNumberOfStars() throws Exception {

        final var solar_system = new HostSystem()
                .setNumberOfStar(1);
        solar_system
                .setDistance(0.0)
                .setName("Solar System");

        hostSystemRepository.save(solar_system);

        final var proxima = new HostSystem()
                .setNumberOfStar(3);
        proxima
                .setDistance(4.)
                .setName("Proxima Centauri");

        hostSystemRepository.save(proxima);

        final var hosts = hostSystemRepository.findAllByNumberOfStar(1);
        assertThat(hosts).hasSize(1);
        assertThat(hosts).extracting(Body::getName).containsExactly("Solar System");

    }


}
