package model;

import java.io.InputStream;
import java.util.Set;


public class UserModel {

    private String nic;
    private String username;
    private String email;
    private String address;
    private String password;
    private String firstName;
    private String lastName;
    private String mobile;
    private String home;
    private ProviderInfo provider;
    private String region;
    private Set<String> services;
    private String connectionType;
    private String connectionStatus;
    private InputStream image;
    private String accountNumber;
    private String iotMeter;
    private String iotId;


    public UserModel(String firstName, String lastName, String mobile) {
    }



    public UserModel(){}


    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
  
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ProviderInfo getProvider() {
        return provider;
    }

    public void setProvider(ProviderInfo provider) {
        this.provider = provider;
    }

    public Set<String> getServices() {
        return services;
    }

    public void setServices(Set<String> services) {
        this.services = services;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public String getIotMeter() {
        return iotMeter;
    }

    public void setIotMeter(String iotMeter) {
        this.iotMeter = iotMeter;
    }

    public String getIotId() {
        return iotId;
    }

    public void setIotId(String iotId) {
        this.iotId = iotId;
    }


    public enum ProviderInfo {
        CEB,
        LECO
    }

    public String getFullName(){
        return this.firstName+ " "+ this.lastName;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(String connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
