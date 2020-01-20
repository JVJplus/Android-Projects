package com.example.android.quakereport;


public class Earthquake {

    /**
     * Magnitude of the earthquake
     */
    private double mMagnitude;

    /**
     * Location of the earthquake
     */
    private String mLocation;

    private long mDate;
    /**
     * Time of the earthquake
     */
    private long mTimeInMilliseconds;
    /**
     * Website URL of the earthquake
     */
    private String mUrl;

    public Earthquake(double magnitude, String location, long date) {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
    }

    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }

    public long getmDate() {
        return mDate;
    }

    public void setmDate(long mDate) {
        this.mDate = mDate;
    }

    /**
     * Returns the magnitude of the earthquake.
     */
    public double getMagnitude() {
        return mMagnitude;
    }

    /**
     * Returns the location of the earthquake.
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Returns the time of the earthquake.
     */
    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    /**
     * Returns the website URL to find more information about the earthquake.
     */
    public String getUrl() {
        return mUrl;
    }


}