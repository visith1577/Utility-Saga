package model;


public class SolarCompanyModel {
    private String id;
    private String CompanyName;
    private String BNum;
    private String username;
    private String ownerNIC;
    private String pwd;

    private String mobile;
    private String landNo;
    private String district;
    private String address;
    private String remarks;

    private String email;

    private ApprovalStatus approvalStatus;

    public enum ApprovalStatus {
        PENDING,
        APPROVED,
        REJECTED
    }

    public SolarCompanyModel(String id,String companyName, String BNum, String username, String ownerNIC, String pwd, String mobile, String landNo, String email, String district, String address, String remarks, ApprovalStatus approvalStatus) {
        this.id= id;
        this.CompanyName = companyName;
        this.BNum = BNum;
        this.username = username;
        this.ownerNIC = ownerNIC;
        this.pwd = pwd;
        this.mobile = mobile;
        this.landNo = landNo;
        this.email= email;
        this.district = district;
        this.address = address;
        this.remarks = remarks;
        this.approvalStatus= approvalStatus;
    }

    public SolarCompanyModel(){}

    public SolarCompanyModel(String BNum, ApprovalStatus approvalStatus){
        this.BNum= BNum;
        this.approvalStatus= approvalStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getBNum() {
        return BNum;
    }

    public void setBNum(String BNum) {
        this.BNum = BNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOwnerNIC() {
        return ownerNIC;
    }

    public void setOwnerNIC(String ownerNIC) {
        this.ownerNIC = ownerNIC;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLandNo() {
        return landNo;
    }

    public void setLandNo(String landNo) {
        this.landNo = landNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
