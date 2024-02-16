package model;

public class SuperAdmineditAdmin {
    private String elecfirstName;
    private String eleclastName;
    private String elecTitle;
    private String elecId;
    private String elecPass;

    private String waterfirstName;
    private String waterlastName;
    private String waterTitle;
    private String waterId;
    private String waterPass;

    private String solarName;
    private String solaruName;
    private String solarlandPhone;
    private String solarId;
    private String solarPass;

    public SuperAdmineditAdmin(String elecfirstName,String eleclastName, String elecTitle, String elecId, String elecPass, String waterfirstName,String waterlastName, String waterTitle, String waterId, String waterPass, String solarName, String solaruName, String solarlandPhone, String solarId, String solarPass) {
        this.elecfirstName = elecfirstName;
        this.eleclastName = eleclastName;
        this.elecTitle = elecTitle;
        this.elecId = elecId;
        this.elecPass = elecPass;
        this.waterfirstName = waterfirstName;
        this.waterlastName = waterlastName;
        this.waterTitle = waterTitle;
        this.waterId = waterId;
        this.waterPass = waterPass;
        this.solarName = solarName;
        this.solaruName = solaruName;
        this.solarlandPhone = solarlandPhone;
        this.solarId = solarId;
        this.solarPass = solarPass;
    }

    public String getElecfirstName() {
        return elecfirstName;
    }

    public void setElecfirstName(String elecfirstName) {
        this.elecfirstName = elecfirstName;
    }

    public String getElecFullName(String elecfirstName, String eleclastName){
        return this.elecfirstName +" " + this.eleclastName;
    }

    public String getEleclastName() {
        return eleclastName;
    }

    public void setEleclastName(String eleclastName) {
        this.eleclastName = eleclastName;
    }

    public String getElecTitle() {
        return elecTitle;
    }

    public void setElecTitle(String elecTitle) {
        this.elecTitle = elecTitle;
    }

    public String getElecId() {
        return elecId;
    }

    public void setElecId(String elecId) {
        this.elecId = elecId;
    }

    public String getElecPass() {
        return elecPass;
    }

    public void setElecPass(String elecPass) {
        this.elecPass = elecPass;
    }

    public String getWaterfirstName() {
        return waterfirstName;
    }

    public void setWaterfirstName(String waterfirstName) {
        this.waterfirstName = waterfirstName;
    }

    public String getWaterlastName() {
        return waterlastName;
    }

    public void setWaterlastName(String waterlastName) {
        this.waterlastName = waterlastName;
    }

    public String getWaterFullName(String waterfirstName, String waterlastName){
        return waterfirstName +" " + waterlastName;
    }

    public String getWaterTitle() {
        return waterTitle;
    }

    public void setWaterTitle(String waterTitle) {
        this.waterTitle = waterTitle;
    }

    public String getWaterId() {
        return waterId;
    }

    public void setWaterId(String waterId) {
        this.waterId = waterId;
    }

    public String getWaterPass() {
        return waterPass;
    }

    public void setWaterPass(String waterPass) {
        this.waterPass = waterPass;
    }

    public String getSolarName() {
        return solarName;
    }

    public void setSolarfirstName(String solarfirstName) {
        this.solarName = solarName;
    }


    public void setSolarName(String solarName) {
        this.solarName = solarName;
    }

    public String getSolaruName() {
        return solaruName;
    }

    public void setSolaruName(String solaruName) {
        this.solaruName = solaruName;
    }

    public String getSolarlandPhone() {
        return solarlandPhone;
    }

    public void setSolarlandPhone(String solarlandPhone) {
        this.solarlandPhone = solarlandPhone;
    }

    public String getSolarId() {
        return solarId;
    }

    public void setSolarId(String solarId) {
        this.solarId = solarId;
    }

    public String getSolarPass() {
        return solarPass;
    }

    public void setSolarPass(String solarPass) {
        this.solarPass = solarPass;
    }
}
