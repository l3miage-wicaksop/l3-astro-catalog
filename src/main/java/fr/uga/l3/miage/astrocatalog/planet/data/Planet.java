package fr.uga.l3.miage.astrocatalog.planet.data;

import fr.uga.l3.miage.astrocatalog.Body;
import fr.uga.l3.miage.astrocatalog.host.data.HostSystem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Objects;

@Entity
public class Planet extends Body {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private float semiMajorAxis;

    private float orbitalPeriod;

    @Column(nullable = false)
    private Date discovery;

    @Column(nullable = false)
    private Boolean confirmed;

    @ManyToOne(optional = false)
    private HostSystem host;

    public Planet() {
    }

    public Type getType() {
        return this.type;
    }

    public float getSemiMajorAxis() {
        return this.semiMajorAxis;
    }

    public float getOrbitalPeriod() {
        return this.orbitalPeriod;
    }

    public Date getDiscovery() {
        return this.discovery;
    }

    public Boolean getConfirmed() {
        return this.confirmed;
    }

    public HostSystem getHost() {
        return this.host;
    }

    public Planet setType(Type type) {
        this.type = type;
        return this;
    }

    public Planet setSemiMajorAxis(float semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
        return this;
    }

    public Planet setOrbitalPeriod(float orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
        return this;
    }

    public Planet setDiscovery(Date discovery) {
        this.discovery = discovery;
        return this;
    }

    public Planet setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
        return this;
    }

    public Planet setHost(HostSystem host) {
        this.host = host;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Planet planet = (Planet) o;
        return Float.compare(planet.getSemiMajorAxis(), getSemiMajorAxis()) == 0 && Float.compare(planet.getOrbitalPeriod(), getOrbitalPeriod()) == 0 && getType() == planet.getType() && Objects.equals(getDiscovery(), planet.getDiscovery()) && Objects.equals(getConfirmed(), planet.getConfirmed()) && Objects.equals(getHost(), planet.getHost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getType(), getSemiMajorAxis(), getOrbitalPeriod(), getDiscovery(), getConfirmed(), getHost());
    }

    @Override
    public String toString() {
        return "Planet{" +
                "type=" + type +
                ", semiMajorAxis=" + semiMajorAxis +
                ", orbitalPeriod=" + orbitalPeriod +
                ", discovery=" + discovery +
                ", confirmed=" + confirmed +
                ", host=" + host +
                "} " + super.toString();
    }

    public enum Type {
        HOT_JUPITER,
        GAS_GIANT,
        EARTH_LIKE,
        SUPER_EARTH
    }

}
