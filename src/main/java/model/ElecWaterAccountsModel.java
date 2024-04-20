package model;

import java.util.List;

public class ElecWaterAccountsModel {
    private String accountNumber;
    private String nic;
    private UserStatus userStatus;
    private MeterStatus meterStatus;
    private IotStatus iotStatus;
    private String region;
    private String subRegion;
    private Integer balance;

    public enum UserStatus{
        ACTIVE,
        INACTIVE
    }

    public enum MeterStatus{
        ACTIVE,
        INACTIVE
    }

    public enum IotStatus{
        YES,
        NO
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

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public MeterStatus getMeterStatus() {
        return meterStatus;
    }

    public void setMeterStatus(MeterStatus meterStatus) {
        this.meterStatus = meterStatus;
    }

    public IotStatus getIotStatus() {
        return iotStatus;
    }

    public void setIotStatus(IotStatus iotStatus) {
        this.iotStatus = iotStatus;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
