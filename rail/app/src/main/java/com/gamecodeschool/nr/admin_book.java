package com.gamecodeschool.nr;

public class admin_book {
    String cityname,checkinDate,checkoutDate,checkinTime,checkoutTime,crisId,name;
    public admin_book() {
    }

    public admin_book(String nameCity, String checkinDate, String checkoutDate, String checkinTime, String checkoutTime, String crisId, String name) {
        this.cityname = cityname;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.crisId = crisId;
        this.name=name;
    }

    public String getCityname() {
        return cityname;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public String getCheckinTime() {
        return checkinTime;
    }

    public String getCheckoutTime() {
        return checkoutTime;
    }

    public String getCrisId() {
        return crisId;
    }

    public String getName(){
        return name;
    }
}
