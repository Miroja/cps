package nl.ict.psa.cps.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Carrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String transporterCode;

    @Column
    private String latitude;

    @Column
    private String longitude;

    @Column
    private Integer fuel;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    private List<Float> tirePressure;

    @Column
    private String driver;

    @Column
    private String note;

    private Boolean checkedRoutePointHistory = false;

    private Boolean hasContainer = false;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    private List<CarrierRoutePoint> carrierRoutePoints = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    private List<CarrierRoutePoint> carrierRoutePointsHistory = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    private List<TransportContainer> transportContainers = new ArrayList<>();

    public Carrier() {
    }

    public Carrier(String transporterCode, String latitude, String longitude, Integer fuel, List<Float> tirePressure, String driver, String note) {
        this.transporterCode = transporterCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.fuel = fuel;
        this.tirePressure = tirePressure;
        this.driver = driver;
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransporterCode() {
        return transporterCode;
    }

    public void setTransporterCode(String transporterCode) {
        this.transporterCode = transporterCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getFuel() {
        return fuel;
    }

    public void setFuel(Integer fuel) {
        this.fuel = fuel;
    }

    public List<Float> getTirePressure() {
        return tirePressure;
    }

    public void setTirePressure(List<Float> tirePressure) {
        this.tirePressure = tirePressure;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public Boolean getCheckedRoutePointHistory() {
        return checkedRoutePointHistory;
    }

    public void setCheckedRoutePointHistory(Boolean checkedRoutePointHistory) {
        this.checkedRoutePointHistory = checkedRoutePointHistory;
    }

    public Boolean getHasContainer() {
        return hasContainer;
    }

    public void setHasContainer(Boolean hasContainer) {
        this.hasContainer = hasContainer;
    }

    public List<CarrierRoutePoint> getCarrierRoutePoints() {
        return carrierRoutePoints;
    }

    public void setCarrierRoutePoints(List<CarrierRoutePoint> carrierRoutePoints) {
        this.carrierRoutePoints = carrierRoutePoints;
    }

    public List<CarrierRoutePoint> getCarrierRoutePointsHistory() {
        return carrierRoutePointsHistory;
    }

    public void setCarrierRoutePointsHistory(List<CarrierRoutePoint> carrierRoutePointsHistory) {
        this.carrierRoutePointsHistory = carrierRoutePointsHistory;
    }

    public List<TransportContainer> getTransportContainers() {
        return transportContainers;
    }

    public void setTransportContainers(List<TransportContainer> transportContainers) {
        this.transportContainers = transportContainers;
    }
}

