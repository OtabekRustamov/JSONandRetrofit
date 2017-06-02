package com.example.root.jsonandretrofit;

/**
 * Created by root on 5/29/17.
 */

public class TimeJson {
    private String time;
    private long milliseconds_since_epoch;
    private String date;

    public TimeJson(String time, long milliseconds_since_epoch, String date) {
        this.time = time;
        this.milliseconds_since_epoch = milliseconds_since_epoch;
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getMilliseconds_since_epoch() {
        return milliseconds_since_epoch;
    }

    public void setMilliseconds_since_epoch(long milliseconds_since_epoch) {
        this.milliseconds_since_epoch = milliseconds_since_epoch;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
