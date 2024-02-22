package model;

public class UserModel {
    private String nic;
    private String username;
    private String email;
    private String address;
    private String mobile;
    private String password;
    private String firstName;
    private String lastName;
    private String connectionType;
    private String connectionStatus;

    public UserModel(String nic, String username, String email, String address, String mobile, String password, String firstName, String lastName, String connectionType, String connectionStatus) {
        this.nic = nic;
        this.username = username;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.connectionType = connectionType;
        this.connectionStatus = connectionStatus;
    }

    public UserModel(String firstName, String lastName, String mobile) {
    }


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getFullName(String firstName, String lastName){
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
}
