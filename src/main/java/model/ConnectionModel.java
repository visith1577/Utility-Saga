package model;

public class ConnectionModel {
    private String requesterName;
    private String accountNumber;
    private String nic;
    private String email;
    private String region;
    private String mobile;
    private String currentAddress;
    private String newAddress;
    private String nearestAccount;
    private ConnectionRequirement connectionRequirements;
    private String connectionType;

    private AccountStatus accountStatus;

    public enum AccountStatus{
        PENDING,
        ADDED,
        REJECTED
    }
    public enum ConnectionRequirement {
        CONNECTION,
        DISCONNECTION
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }

    public String getNearestAccount() {
        return nearestAccount;
    }

    public void setNearestAccount(String nearestAccount) {
        this.nearestAccount = nearestAccount;
    }

    public ConnectionRequirement getConnectionRequirements() {
        return connectionRequirements;
    }

    public void setConnectionRequirements(ConnectionRequirement connectionRequirements) {
        this.connectionRequirements = connectionRequirements;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
