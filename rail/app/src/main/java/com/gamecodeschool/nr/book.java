package com.gamecodeschool.nr;

public class book {

    private String cityName;
    private String indate;
    private String outdate;
    private String checkInTime;
    private String checkOutTime;

    public book(String cityName, String date, String checkInTime, String checkOutTime,String outdate) {

        this.cityName = cityName;
        this.indate = date;
        this.outdate=outdate;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;

    }

    public String getCityName() {
        return cityName;
    }

    public String getDate() {
        return indate;
    }
    public String getOutdate(){
        return outdate;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }
}
