package fr.uga.l3.miage.astrocatalog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Body {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float mass;

    private Double distance;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public float getMass() {
        return this.mass;
    }

    public double getDistance() {
        return this.distance;
    }

    public Body setName(String name) {
        this.name = name;
        return this;
    }

    public Body setMass(float mass) {
        this.mass = mass;
        return this;
    }

    public Body setDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Body body = (Body) o;
        return Float.compare(body.getMass(), getMass()) == 0 && Double.compare(body.getDistance(), getDistance()) == 0 && Objects.equals(getId(), body.getId()) && Objects.equals(getName(), body.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getMass(), getDistance());
    }

    @Override
    public String toString() {
        return "Body{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mass=" + mass +
                ", distance=" + distance +
                '}';
    }
}
