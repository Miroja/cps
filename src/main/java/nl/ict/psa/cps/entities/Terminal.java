package nl.ict.psa.cps.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String latitude;

    @Column
    private String longitude;

    @Column
    private String bound1Lat;

    @Column
    private String bound1Lon;

    @Column
    private String bound2Lat;

    @Column
    private String bound2Lon;


    public Terminal() {}

    public Terminal(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Terminal(String name, String latitude, String longitude, String bound1Lat, String bound1Lon, String bound2Lat, String bound2Lon) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bound1Lat = bound1Lat;
        this.bound1Lon = bound1Lon;
        this.bound2Lat = bound2Lat;
        this.bound2Lon = bound2Lon;
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

    public String getBound1Lat() {
        return bound1Lat;
    }

    public void setBound1Lat(String bound1Lat) {
        this.bound1Lat = bound1Lat;
    }

    public String getBound1Lon() {
        return bound1Lon;
    }

    public void setBound1Lon(String bound1Lon) {
        this.bound1Lon = bound1Lon;
    }

    public String getBound2Lat() {
        return bound2Lat;
    }

    public void setBound2Lat(String bound2Lat) {
        this.bound2Lat = bound2Lat;
    }

    public String getBound2Lon() {
        return bound2Lon;
    }

    public void setBound2Lon(String bound2Lon) {
        this.bound2Lon = bound2Lon;
    }
}
