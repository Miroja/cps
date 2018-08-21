package nl.ict.psa.cps.entities;

import javax.persistence.*;

@Entity
@Table
public class YardCrane {
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
    private String note;

    public YardCrane(String transporterCode, String latitude, String longitude, String note) {
        this.transporterCode = transporterCode;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
