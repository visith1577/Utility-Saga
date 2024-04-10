package model;

import java.util.UUID;

public class ElectricityAdminModel {
    private String id;
    private String region;
    private String contactNumber;
    private String email;
    private String password;

    public ElectricityAdminModel(){
        UUID uuid= UUID.randomUUID();
        id= getId()+ uuid;
    }

    public ElectricityAdminModel(String id, String region, String contactNumber, String email, String password) {
        this.id = id;
        this.region = region;
        this.contactNumber = contactNumber;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
