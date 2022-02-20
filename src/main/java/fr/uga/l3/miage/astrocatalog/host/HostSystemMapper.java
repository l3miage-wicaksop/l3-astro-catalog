package fr.uga.l3.miage.astrocatalog.host;

import fr.uga.l3.miage.astrocatalog.host.data.HostSystem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HostSystemMapper {

    HostSystemMapper INSTANCE = null;

    HostSystemDTO toDTO(HostSystem hostSystem);

    List<HostSystemDTO> toDTOs(List<HostSystem> hostSystem);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "planets", ignore = true)
    HostSystem toEntity(HostSystemDTO dto);

}
