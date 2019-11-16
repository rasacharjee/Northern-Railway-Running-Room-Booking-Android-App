package com.gamecodeschool.nr;

public class book {

    private String cityName;
    private String checkinDate;
    private String checkoutDate;
    private String checkinTime;
    private String checkoutTime;
    private String status;
    private String bId;
    private String uid;

    public book(){

    }

    public book(String cityName, String checkinDate, String checkInTime, String checkOutTime, String checkoutDate, String status, String bId,String uid) {

        this.cityName = cityName;
        this.checkinDate = checkinDate;
        this.checkoutDate=checkoutDate;
        this.checkinTime = checkInTime;
        this.checkoutTime = checkOutTime;
        this.status=status;
        this.bId=bId;
        this.uid=uid;

    }

    public String getCityName() {
        return cityName;
    }

    public String getDate() {
        return checkinDate;
    }
    public String getOutdate(){
        return checkoutDate;
    }

    public String getCheckInTime() {
        return checkinTime;
    }

    public String getCheckOutTime() {
        return checkoutTime;
    }

    public String getStatus(){
        return status;
    }

    public String getKey() {
        return bId;
    }

    public String getUid(){ return uid;}

  /*  public void setcityName(String cityName) {
        this.cityName = cityName;
    }

    public void setcheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public void setcheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void setcheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    public void setcheckoutTime(String checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public void setbId(String bId) {
        this.bId = bId;
    } */

}
