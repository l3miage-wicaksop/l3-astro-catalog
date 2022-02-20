package fr.uga.l3.miage.astrocatalog;

import fr.uga.l3.miage.astrocatalog.host.HostSystemDTO;
import fr.uga.l3.miage.astrocatalog.host.HostSystemMapper;
import fr.uga.l3.miage.astrocatalog.host.data.HostSystem;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MappingTest {

    @Test
    void shouldMapToDTO() throws Exception {

        final var solar_system = new HostSystem()
                .setNumberOfStar(1);
        solar_system
                .setDistance(.0)
                .setName("Solar System");

        var dto = HostSystemMapper.INSTANCE.toDTO(solar_system);

        assertThat(dto.getName()).isEqualTo(solar_system.getName());

    }


    @Test
    void shouldMapToEntity() throws Exception {

        final var dto = new HostSystemDTO()
                .setNumberOfStar(1)
                .setDistance(.0)
                .setName("Solar System");

        var entity = HostSystemMapper.INSTANCE.toEntity(dto);

        assertThat(entity.getName()).isEqualTo(dto.getName());

    }

}
