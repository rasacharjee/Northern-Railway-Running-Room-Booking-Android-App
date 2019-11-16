package com.gamecodeschool.nr;

public class database_book_java_class {

        String bId,crisId,checkinTime,checkoutTime,checkinDate,checkoutDate, uid;
        int trainNo;
        String status;
        public database_book_java_class() {
        }

        public database_book_java_class(String bId, String crisId, int trainNo, String checkinTime, String checkoutTime, String checkinDate, String checkoutDate, String status, String uid) {
            this.bId = bId;
            this.crisId = crisId;
            this.trainNo = trainNo;
            this.checkinTime = checkinTime;
            this.checkoutTime = checkoutTime;
            this.checkinDate = checkinDate;
            this.checkoutDate = checkoutDate;
            this.status=status;
            this.uid=uid;
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


}
