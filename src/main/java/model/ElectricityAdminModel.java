package model;

import java.util.UUID;

public class ElectricityAdminModel {
    private String id;
    private String region;
    private String contactNumber;
    private String email;
    private String password;

    private UtilityType utilityType;
    private String empId;
    private String uname;
    private String firstname;
    private String lastname;
    private Role role;
    private String mobile;
    public enum Role{
        MAIN,
        REGIONAL
    }


    public enum UtilityType {
        CEB,
        LECO
    }

    public ElectricityAdminModel(){
        UUID uuid= UUID.randomUUID();
        id= getId()+ uuid;
    }

    public ElectricityAdminModel(String id, String region, String contactNumber, String email, String password, UtilityType utilityType,String empId, String uname, String firstname, String lastname, Role role, String mobile) {
        this.id = id;
        this.region = region;
        this.contactNumber = contactNumber;
        this.email = email;
        this.password = password;
        this.utilityType = utilityType;
        this.empId= empId;
        this.uname = uname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.mobile= mobile;
    }

    public ElectricityAdminModel(String id, String region, String contactNumber, String email, String password,String empId, String uname, String firstname, String lastname, Role role, String mobile) {
        this.id = id;
        this.region = region;
        this.contactNumber = contactNumber;
        this.email = email;
        this.password = password;
        this.empId= empId;
        this.uname = uname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.mobile= mobile;
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

    public UtilityType getUtilityType() {
        return utilityType;
    }

    public void setUtilityType(UtilityType utilityType) {
        this.utilityType = utilityType;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
