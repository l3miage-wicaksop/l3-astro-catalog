package fr.uga.l3.miage.astrocatalog.host;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Accessors(chain = true)
public class HostSystemDTO {

    @Null
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @Min(0)
    private Float mass;

    @NotNull
    @Min(0)
    private Double distance;

    @NotNull
    @Min(1)
    private Integer numberOfStar;



    public String getName() {
        return name;
    }



    public HostSystemDTO setName(String name) {
        this.name = name;
        return this;
    }



    public HostSystemDTO setNumberOfStar(Integer numberOfStar) {
        this.numberOfStar = numberOfStar;
        return this;
    }



    public HostSystemDTO setDistance(Double distance) {
        this.distance = distance;
        return this;
    }


    public HostSystemDTO setMass(Float mass) {
        this.mass = mass;
        return this;
    }


    

    
}
