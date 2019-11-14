package com.gamecodeschool.nr;

public class admin_book {
    String nameCity,checkinDate,checkoutDate,checkinTime,checkoutTime,crisId;
    public admin_book() {
    }

    public admin_book(String nameCity, String dateIn, String dateOut, String timeIn, String timeOut, String crisId) {
        this.nameCity = nameCity;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.crisId = crisId;
    }

    public String getNameCity() {
        return nameCity;
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
}
