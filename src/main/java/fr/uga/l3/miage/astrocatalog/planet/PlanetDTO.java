package fr.uga.l3.miage.astrocatalog.planet;

import fr.uga.l3.miage.astrocatalog.host.HostSystemDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
public class PlanetDTO {

    @Null
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @Positive
    @NotNull
    private Float mass;

    @NotNull
    private String type;

    @NotNull
    @Positive
    private float semiMajorAxis;

    @Positive
    private float orbitalPeriod;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date discovery;

    @NotNull
    private Boolean confirmed;

    @Null
    private HostSystemDTO host;

}
