package com.gamecodeschool.nr;

public class driver_credentials {
    String dId,dEmail,dPhn,dName;
    public driver_credentials()
    {

    }

    public driver_credentials(String dId, String dEmail, String dPhn, String dName) {
        this.dId = dId;
        this.dEmail = dEmail;
        this.dPhn = dPhn;
        this.dName = dName;
    }

    public String getdId() {
        return dId;
    }

    public String getdEmail() {
        return dEmail;
    }

    public String getdPhn() {
        return dPhn;
    }

    public String getdName() {
        return dName;
    }
}
