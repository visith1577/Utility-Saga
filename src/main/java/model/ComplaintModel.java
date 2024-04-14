package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class ComplaintModel {

    private String complaint_no;
    private Complaint_Category complaint_category;
    private Complaint_Type complaint_type;
    private String account_number;
    private String nic;
    private String email;
    private String phoneNumber;
    private String Complaint_description;
    private Complaint_Type_Water complaintTypeWater;

    public enum Complaint_Category{
        Breakdown,

        ServiceRequest
    }

    public enum Complaint_Type{
        BillingIssues,
        ConnectDisconnect,
        PowerOutage,
        VoltageFrequency,
        SmartMeter,
        Quality,
        Other



    }

    public enum Complaint_Type_Water{
        MainLeak,
        ConnectionLeak,
        NoWater,
        LowPressure,
        LeakNearby,
        Quality,
        Other



    }

    public ComplaintModel() {
        UUID uuid = UUID.randomUUID();
        complaint_no = getAccount_number() + uuid;
    }

    public ComplaintModel(String complaintNumber, String complaintCategory, String complaintType, String accountNumber, String complaintStatus) {
    }

//    public String getComplaint_category() {
//        return complaint_category;
//    }

//    public void setComplaint_category(String complaint_category) {
//        this.complaint_category = complaint_category;
//    }
//
//    public String getComplaint_type() {
//        return complaint_type;
//    }
//
//    public void setComplaint_type(String complaint_type) {
//        this.complaint_type = complaint_type;
//    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getComplaint_description() {
        return Complaint_description;
    }

    public void setComplaint_description(String complaint_description) {
        Complaint_description = complaint_description;
    }

    public String getComplaint_no() {
        return complaint_no;
    }

    public void setComplaint_no(String complaint_no) {this.complaint_no = complaint_no;}

    public Complaint_Category getComplaint_category() {
        return complaint_category;
    }

    public void setComplaint_category(Complaint_Category complaint_category) {
        this.complaint_category = complaint_category;
    }

    public Complaint_Type getComplaint_type() {
        return complaint_type;
    }

    public void setComplaint_type(Complaint_Type complaint_type) {
        this.complaint_type = complaint_type;
    }

    public Complaint_Type_Water getComplaintTypeWater() {
        return complaintTypeWater;
    }

    public void setComplaintTypeWater(Complaint_Type_Water complaintTypeWater) {
        this.complaintTypeWater = complaintTypeWater;
    }
}
