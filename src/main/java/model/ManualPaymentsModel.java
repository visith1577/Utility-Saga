package model;

import java.time.LocalDateTime;

public class ManualPaymentsModel {
    private String account_number;
    private String nic;
    private String amount;
    private LocalDateTime date;

    public ManualPaymentsModel(String account_number, String nic, String amount, LocalDateTime date) {
        this.account_number = account_number;
        this.nic = nic;
        this.amount = amount;
        this.date = date;
    }

    public ManualPaymentsModel() {

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
