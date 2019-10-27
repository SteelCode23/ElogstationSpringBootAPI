package com.elogstation.api.elogstationapi.util;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;

public class DateRange {

    String sub;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fromTime;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date toTime;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return "DateRange{" +
                "sub='" + sub + '\'' +
                ", fromTime=" + fromTime +
                ", toTime=" + toTime +
                '}';
    }
}
