package model;

public class ComplaintRAdmin extends ComplaintModel{
    public ComplaintRAdmin(String complaint_number, String complaint_category, String complaint_type, String account_number, ComplaintStatus complaint_status) {
        super(complaint_number, complaint_category, complaint_type, account_number, String.valueOf(complaint_status));
    }

    public enum ComplaintStatus {
        ACTIVE,
        PENDING,
        DONE
    }


    public void setUserNic(UserModel user) {
    }
}
