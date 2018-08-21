package nl.ict.psa.cps.entities;

import nl.ict.psa.cps.entities.CarrierRoutePoint;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class HeatMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    private List<CarrierRoutePoint> heatMapRoutePoints = new ArrayList<>();


    public HeatMap() {
    }

    public HeatMap(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CarrierRoutePoint> getHeatMapRoutePoints() {
        return heatMapRoutePoints;
    }

    public void setHeatMapRoutePoints(List<CarrierRoutePoint> heatMapRoutePoints) {
        this.heatMapRoutePoints = heatMapRoutePoints;
    }
}
