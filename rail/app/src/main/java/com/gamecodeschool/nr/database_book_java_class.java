package com.gamecodeschool.nr;

public class database_book_java_class {

        String bId,crisId,checkinTime,checkoutTime,checkinDate,checkoutDate;
        int trainNo;
        String status;
        String uid;
        String cityname;
        String name;
        public database_book_java_class() {
        }

        public database_book_java_class(String bId, String crisId, int trainNo, String checkinTime, String checkoutTime, String checkinDate, String checkoutDate, String status, String uid, String cityname,String name) {
            this.bId = bId;
            this.crisId = crisId;
            this.trainNo = trainNo;
            this.checkinTime = checkinTime;
            this.checkoutTime = checkoutTime;
            this.checkinDate = checkinDate;
            this.checkoutDate = checkoutDate;
            this.status=status;
            this.uid=uid;
            this.cityname=cityname;
            this.name=name;
        }

        public String getbId() {
            return bId;
        }

        public String getCrisId() {
            return crisId;
        }

        public int getTrainNo() {
            return trainNo;
        }

        public String getCheckinTime() {
            return checkinTime;
        }

        public String getCheckoutTime() {
            return checkoutTime;
        }

        public String getCheckinDate() {
            return checkinDate;
        }

        public String getCheckoutDate() {
            return checkoutDate;
        }
        public String getStatus(){
            return status;
        }
        public String getUID(){
            return uid;
        }
        public String getCityname(){
            return cityname;
        }

        public String getName(){
            return name;
        }

}
