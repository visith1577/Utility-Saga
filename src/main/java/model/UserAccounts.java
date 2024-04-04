package model;

import java.util.List;

public class UserAccounts {
    private String bill_id;
    private String account_number;
    private String amount;
    private String billed_date;
    private String dueDate;

    private List<String> account_list;


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

    public List<String> getAccount_list() {
        return account_list;
    }

    public void setAccount_list(List<String> account_list) {
        this.account_list = account_list;
    }

    public void add_account(String account_number) {
        this.account_list.add(account_number);
    }
}
