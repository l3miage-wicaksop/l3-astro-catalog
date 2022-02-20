package fr.uga.l3.miage.astrocatalog.planet;

import fr.uga.l3.miage.astrocatalog.host.HostSystemMapper;
import fr.uga.l3.miage.astrocatalog.planet.data.Planet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = HostSystemMapper.class)
public interface PlanetMapper {

    PlanetMapper INSTANCE = null;

    PlanetDTO toDTO(Planet planet);

    List<PlanetDTO> toDTOs(List<Planet> planets);

    @Mapping(target = "host", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "distance", ignore = true)
    Planet toEntity(PlanetDTO dto);


}
