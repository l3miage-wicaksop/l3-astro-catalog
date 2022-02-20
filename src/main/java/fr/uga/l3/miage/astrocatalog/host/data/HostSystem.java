package fr.uga.l3.miage.astrocatalog.host.data;

import fr.uga.l3.miage.astrocatalog.Body;
import fr.uga.l3.miage.astrocatalog.planet.data.Planet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import java.util.List;
import java.util.Objects;

@Entity
public class HostSystem extends Body {

    @Column(nullable = false)
    private Integer numberOfStar;

    @OneToMany(mappedBy = "host")
    @OrderColumn(name = "PLANET_ORDER")
    private List<Planet> planets;

    public Integer getNumberOfStar() {
        return this.numberOfStar;
    }

    public List<Planet> getPlanets() {
        return this.planets;
    }

    public HostSystem setNumberOfStar(int numberOfStar) {
        this.numberOfStar = numberOfStar;
        return this;
    }

    public HostSystem setPlanets(List<Planet> planets) {
        this.planets = planets;
        return this;
    }

    @Override
    public String toString() {
        return "HostSystem{" +
                "numberOfStar=" + numberOfStar +
                ", planets=" + planets +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HostSystem that = (HostSystem) o;
        return Objects.equals(getNumberOfStar(), that.getNumberOfStar()) && Objects.equals(getPlanets(), that.getPlanets());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNumberOfStar(), getPlanets());
    }
}
