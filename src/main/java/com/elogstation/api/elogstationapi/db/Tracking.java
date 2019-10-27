package com.elogstation.api.elogstationapi.db;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tracking")
public class Tracking extends AuditModel {

    @Id
    @GeneratedValue(generator = "id_generator")
    @SequenceGenerator(
            name = "id_generator",
            sequenceName = "id_sequence",
            initialValue = 1000
    )
    private Long id;

    private String sub;
    private String deviceId;
    private String eldId;
    private String statusType;


    //eld starts
    private String engineState;
    private String externalId;
    private String vin;
    private double rpm;
    private double speed;
    private double odometer;
    private double tripDistance;
    private double engineHours;
    private double tripHours;
    private double voltage;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gpsDateTime;
    private double latitude;
    private double longitude;
    private int gpsSpeed;
    private int course;
    private int numSats;
    private int mslAlt;
    private double dop;
    private int sequence;
    private String firmwareVersion;
    //eld ends

    public String getEngineState() {
        return engineState;
    }

    public void setEngineState(String engineState) {
        this.engineState = engineState;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public double getRpm() {
        return rpm;
    }

    public void setRpm(double rpm) {
        this.rpm = rpm;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getOdometer() {
        return odometer;
    }

    public void setOdometer(double odometer) {
        this.odometer = odometer;
    }

    public double getTripDistance() {
        return tripDistance;
    }

    public void setTripDistance(double tripDistance) {
        this.tripDistance = tripDistance;
    }

    public double getEngineHours() {
        return engineHours;
    }

    public void setEngineHours(double engineHours) {
        this.engineHours = engineHours;
    }

    public double getTripHours() {
        return tripHours;
    }

    public void setTripHours(double tripHours) {
        this.tripHours = tripHours;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public Date getGpsDateTime() {
        return gpsDateTime;
    }

    public void setGpsDateTime(Date gpsDateTime) {
        this.gpsDateTime = gpsDateTime;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getGpsSpeed() {
        return gpsSpeed;
    }

    public void setGpsSpeed(int gpsSpeed) {
        this.gpsSpeed = gpsSpeed;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getNumSats() {
        return numSats;
    }

    public void setNumSats(int numSats) {
        this.numSats = numSats;
    }

    public int getMslAlt() {
        return mslAlt;
    }

    public void setMslAlt(int mslAlt) {
        this.mslAlt = mslAlt;
    }

    public double getDop() {
        return dop;
    }

    public void setDop(double dop) {
        this.dop = dop;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getEldId() {
        return eldId;
    }

    public void setEldId(String eldId) {
        this.eldId = eldId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
