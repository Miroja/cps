package nl.ict.psa.cps.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class CarrierRoutePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String latitude;

    @Column
    private String longitude;

    @Temporal(TemporalType.TIMESTAMP)
    private Date DateTime;


    public CarrierRoutePoint() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }
}
