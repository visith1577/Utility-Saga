package backend.models.auth;

import java.util.Date;

public class electricityAnalysis {
    private int id;
    private String budgetName;
    private Date start;
    private Date end;
    private int units;

    public electricityAnalysis(int id, String budgetName, Date start, Date end, int units) {
        this.id = id;
        this.budgetName = budgetName;
        this.start = start;
        this.end = end;
        this.units = units;
    }

    public electricityAnalysis(String budgetName, Date start, Date end, int units) {
        this.budgetName = budgetName;
        this.start = start;
        this.end = end;
        this.units = units;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

}
