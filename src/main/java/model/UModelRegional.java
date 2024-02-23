package model;

public class UModelRegional {
    private String lastName;
    private String address;
    private String mobile;
    private String connectionStatus;
    private String connectionType;
    private String nic;
    private String firstName;




    public UModelRegional(String lastName, String address, String mobile, String connectionStatus, String connectionType, String nic, String firstName) {
        this.lastName = lastName;
        this.address = address;
        this.mobile = mobile;
        this.connectionStatus = connectionStatus;
        this.connectionType = connectionType;
        this.nic = nic;
        this.firstName = firstName;
    }



    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName(String firstName, String lastName){
        return this.firstName+ " "+ this.lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setConnectionStatus(String connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }
}
