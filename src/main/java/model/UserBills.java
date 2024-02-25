package model;

public class UserBills {
    private String bill_id;
    private String account_number;
    private String amount;
    private String billed_date;
    private String dueDate;


    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBilled_date() {
        return billed_date;
    }

    public void setBilled_date(String billed_date) {
        this.billed_date = billed_date;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
