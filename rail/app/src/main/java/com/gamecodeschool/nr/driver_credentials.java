package com.gamecodeschool.nr;

public class driver_credentials {
    String dId,dEmail,dPhn,dName,duid;
    public driver_credentials()
    {

    }

    public driver_credentials(String dId, String dEmail, String dPhn, String dName, String duid) {
        this.dId = dId;
        this.dEmail = dEmail;
        this.dPhn = dPhn;
        this.dName = dName;
        this.duid=duid;
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




    public String getDuid(){
        return duid;
    }
}
