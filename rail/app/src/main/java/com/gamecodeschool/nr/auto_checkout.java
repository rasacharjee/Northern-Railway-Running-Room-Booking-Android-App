package com.gamecodeschool.nr;

public class auto_checkout {

    private String checkoutDate,checkoutTime,status,bId;

    public auto_checkout(){}

    public auto_checkout(String checkoutDate, String checkoutTime, String status,String bId) {
        this.checkoutDate = checkoutDate;
        this.checkoutTime = checkoutTime;
        this.status = status;
        this.bId=bId;

    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public String getCheckoutTime() {
        return checkoutTime;
    }

    public String getStatus() {
        return status;
    }

    public String getbId(){return bId;}

}
