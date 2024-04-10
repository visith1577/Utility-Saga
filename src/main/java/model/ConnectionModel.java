package model;

public class ConnectionModel {
    private String requester_name;
    private String account_number;
    private String nic;
    private String email;
    private String region;
    private String mobile;
    private String current_address;
    private String new_address;
    private String nearest_account;
    private ConnectionRequirement connection_requirements;
    private String connection_type;

    public enum ConnectionRequirement {
        CONNECTION,
        DISCONNECTION
    }

    public String getRequester_name() {
        return requester_name;
    }

    public void setRequester_name(String requester_name) {
        this.requester_name = requester_name;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCurrent_address() {
        return current_address;
    }

    public void setCurrent_address(String current_address) {
        this.current_address = current_address;
    }

    public String getNew_address() {
        return new_address;
    }

    public void setNew_address(String new_address) {
        this.new_address = new_address;
    }

    public String getNearest_account() {
        return nearest_account;
    }

    public void setNearest_account(String nearest_account) {
        this.nearest_account = nearest_account;
    }

    public ConnectionRequirement getConnection_requirements() {
        return connection_requirements;
    }

    public void setConnection_requirements(ConnectionRequirement connection_requirements) {
        this.connection_requirements = connection_requirements;
    }

    public String getConnection_type() {
        return connection_type;
    }

    public void setConnection_type(String connection_type) {
        this.connection_type = connection_type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
