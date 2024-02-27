package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class ComplaintModel {

    private String complaint_no;
    private String complaint_category;
    private String complaint_type;
    private String account_number;
    private String nic;
    private String email;
    private String phoneNumber;
    private String Complaint_description;

    public ComplaintModel() {
        UUID uuid = UUID.randomUUID();
        complaint_no = getAccount_number() + uuid;
    }

    public String getComplaint_category() {
        return complaint_category;
    }

    public void setComplaint_category(String complaint_category) {
        this.complaint_category = complaint_category;
    }

    public String getComplaint_type() {
        return complaint_type;
    }

    public void setComplaint_type(String complaint_type) {
        this.complaint_type = complaint_type;
    }

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
}
