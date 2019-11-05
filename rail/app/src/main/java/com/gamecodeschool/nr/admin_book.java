package com.gamecodeschool.nr;

public class admin_book {
    String nameCity,dateIn,dateOut,timeIn,timeOut,crisId;
    public admin_book() {
    }

    public admin_book(String nameCity, String dateIn, String dateOut, String timeIn, String timeOut, String crisId) {
        this.nameCity = nameCity;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.crisId = crisId;
    }

    public String getNameCity() {
        return nameCity;
    }

    public String getDateIn() {
        return dateIn;
    }

    public String getDateOut() {
        return dateOut;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public String getCrisId() {
        return crisId;
    }
}
