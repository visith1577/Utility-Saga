package model;

public class UserRAdmin extends UserModel{
    private String id;
    private String tel;
    private String region;
    private UtilityType utilityType;
    private Role role;

    public Role getRole() {
        return role;
    }

    public enum UtilityType{
        CEB,
        LECO
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public enum Role {
        MAIN,
        REGIONAL,
        SUPERADMIN
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String getRegion() {
        return region;
    }

    @Override
    public void setRegion(String region) {
        this.region = region;
    }

    public UtilityType getUtilityType() {
        return utilityType;
    }

    public void setUtilityType(UtilityType utilityType) {
        this.utilityType = utilityType;
    }
}
