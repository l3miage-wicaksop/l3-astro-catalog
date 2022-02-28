package fr.uga.l3.miage.astrocatalog;

import fr.uga.l3.miage.astrocatalog.host.HostSystemDTO;
import fr.uga.l3.miage.astrocatalog.host.HostSystemMapper;
import fr.uga.l3.miage.astrocatalog.host.data.HostSystem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MappingTest {

    @Autowired
    HostSystemMapper hostSystemMapper;

    @Test
    void shouldMapToDTO() throws Exception {

        final var solar_system = new HostSystem()
                .setNumberOfStar(1);
        solar_system
                .setMass(1)
                .setDistance(.0)
                .setName("Solar System");

        var dto = hostSystemMapper.toDTO(solar_system);

        assertThat(dto.getName()).isEqualTo(solar_system.getName());

    }


    @Test
    void shouldMapToEntity() throws Exception {

        final var dto = new HostSystemDTO()
                .setNumberOfStar(1)
                .setDistance(.0)
                .setMass(1f)
                .setName("Solar System");

        var entity = hostSystemMapper.toEntity(dto);

        assertThat(entity.getName()).isEqualTo(dto.getName());

    }

}
