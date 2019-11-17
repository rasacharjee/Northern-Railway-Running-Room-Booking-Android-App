package com.gamecodeschool.nr;

public class book {

    private String cityname;
    private String checkinDate;
    private String checkoutDate;
    private String checkinTime;
    private String checkoutTime;
    private String status;
    private String bId;
    private String uid;

    public book(){

    }

    public book(String cityname, String checkinDate, String checkInTime, String checkOutTime, String checkoutDate, String status, String bId,String uid) {

        this.cityname = cityname;
        this.checkinDate = checkinDate;
        this.checkoutDate=checkoutDate;
        this.checkinTime = checkInTime;
        this.checkoutTime = checkOutTime;
        this.status=status;
        this.bId=bId;
        this.uid=uid;

    }

    public String getCityName() {
        return cityname;
    }

    public String getCheckInDate() {
        return checkinDate;
    }
    public String getCheckOutdate(){
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

    public String getbId() {
        return bId;
    }

    public String getUid(){ return uid;}

}
