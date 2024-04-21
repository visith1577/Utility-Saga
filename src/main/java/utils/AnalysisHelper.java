package utils;

import DAO.dao.AnalyticDao;
import model.IoTModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnalysisHelper {

    public List<IoTModel> getDifferenceBetweenReadings(String account) throws SQLException {
        AnalyticDao analyticDao = new AnalyticDao();
        List<IoTModel> data = analyticDao.getMeterDataMonthly(account);
        List<IoTModel> diffData = new ArrayList<>();

        for (int i = 1; i < data.size(); i++) {
            IoTModel prev = data.get(i - 1);
            IoTModel curr = data.get(i);

            IoTModel diffModel = new IoTModel();
            diffModel.setId(curr.getId());
            diffModel.setDate(curr.getDate());
            diffModel.setTime(curr.getTime());
            diffModel.setData(curr.getData() - prev.getData());

            diffData.add(diffModel);
        }

        return diffData;
    }


    public double calculateDomesticBill(double unit1, double unit2, double unit3) {
        double bill = 0.0;
        double fixedCharge = 0.0;
        boolean isTimeOfUse = (unit2 > 0 || unit3 > 0);

        if (isTimeOfUse) {

            bill += unit1 * 70.0; // Day Time
            bill += unit2 * 90.0; // Peak Time
            bill += unit3 * 30.0; // Off-Peak Time
            fixedCharge = 2000.0;
        } else {

            double totalUnits = unit1;
            double remainingUnits = totalUnits;

            if (totalUnits <= 30) {
                bill = totalUnits * 8.0;
                fixedCharge = 150.0;
            } else if (totalUnits <= 60) {
                bill = 30 * 8.0 + (totalUnits - 30) * 20.0;
                fixedCharge = 300.0;
            } else {
                remainingUnits = totalUnits - 60;
                bill = 30 * 8.0 + 30 * 20.0; // For the first 60 units

                if (remainingUnits <= 30) {
                    bill += remainingUnits * 25.0;
                } else if (remainingUnits <= 60) {
                    bill += 30 * 25.0 + (remainingUnits - 30) * 30.0;
                    fixedCharge = 400.0;
                } else if (remainingUnits <= 120) {
                    bill += 30 * 25.0 + 30 * 30.0 + (remainingUnits - 60) * 50.0;
                    fixedCharge = 1000.0;
                } else if (remainingUnits <= 180) {
                    bill += 30 * 25.0 + 30 * 30.0 + 60 * 50.0 + (remainingUnits - 120) * 50.0;
                    fixedCharge = 1500.0;
                } else {
                    bill += 30 * 25.0 + 30 * 30.0 + 60 * 50.0 + 60 * 50.0 + (remainingUnits - 180) * 75.0;
                    fixedCharge = 2000.0;
                }
            }
        }

        double amt =  bill + fixedCharge;
        double tax = amt * 0.025;

        amt += tax;
        return amt;
    }

    public double calculateDomesticBillWater(double unit) {
        double bill = 0.0;
        double fixedCharge = 0.0;

        if (unit <= 5) {
            bill = unit * 60.0;
            fixedCharge = 300.0;
        } else if (unit <= 10) {
            bill = 5 * 60.0 + (unit - 5) * 80.0;
            fixedCharge = 300.0;
        } else if (unit <= 15) {
            bill = 5 * 60.0 + 5 * 80.0 + (unit - 10) * 100.0;
            fixedCharge = 300.0;
        } else if (unit <= 20) {
            bill = 5 * 60.0 + 5 * 80.0 + 5 * 100.0 + (unit - 15) * 110.0;
            fixedCharge = 400.0;
        } else if (unit <= 25) {
            bill = 5 * 60.0 + 5 * 80.0 + 5 * 100.0 + 5 * 110.0 + (unit - 20) * 130.0;
            fixedCharge = 500.0;
        } else if (unit <= 30) {
            bill = 5 * 60.0 + 5 * 80.0 + 5 * 100.0 + 5 * 110.0 + 5 * 130.0 + (unit - 25) * 160.0;
            fixedCharge = 600.0;
        } else if (unit <= 40){
            bill = 5 * 60.0 + 5 * 80.0 + 5 * 100.0 + 5 * 110.0 + 5 * 130.0 + 5 * 160.0 + (unit - 30) * 180.0;
            fixedCharge = 1500.0;
        } else if (unit <= 50){
            bill = 5 * 60.0 + 5 * 80.0 + 5 * 100.0 + 5 * 110.0 + 5 * 130.0 + 5 * 160.0 + 10 * 180.0 + (unit - 40) * 210.0;
            fixedCharge = 3000.0;
        } else if (unit <= 75) {
            bill = 5 * 60.0 + 5 * 80.0 + 5 * 100.0 + 5 * 110.0 + 5 * 130.0 + 5 * 160.0 + 10 * 180.0 + 10 * 210.0 + (unit - 50) * 240.0;
            fixedCharge = 3500.0;
        } else if (unit <= 100){
            bill = 5 * 60.0 + 5 * 80.0 + 5 * 100.0 + 5 * 110.0 + 5 * 130.0 + 5 * 160.0 + 10 * 180.0 + 10 * 210.0 + 25 * 230.0 + (unit - 75) * 270.0;
            fixedCharge = 4000.0;
        } else {
            bill = 5 * 60.0 + 5 * 80.0 + 5 * 100.0 + 5 * 110.0 + 5 * 130.0 + 5 * 160.0 + 10 * 180.0 + 10 * 210.0 + 25 * 230.0 + 25 * 270.0 + (unit - 100) * 300.0;
            fixedCharge = 4500.0;
        }

        double tax = (bill + fixedCharge) * 0.18;

        return bill + fixedCharge + tax;
    }


    public static double calculateOtherCategoryBill(double unit1, Double unit2, Double unit3, String category) {
        double bill = 0.0;
        double fixedCharge = 0.0;
        double demandCharge = 0.0;
        double totalUnits = unit1;

        if (unit2 != null) {
            totalUnits = unit1 + unit2 + unit3;
        }

        switch (category.toLowerCase()) {
            case "ip1-1":
                if (totalUnits <= 300) {
                    bill = totalUnits * 13.5;
                    fixedCharge = 300.0;
                } else {
                    bill = 300 * 13.5 + (totalUnits - 300) * 21.5;
                    fixedCharge = 1000.0;
                }
                break;
            case "ip1-2":
                bill = totalUnits * 21.5;
                fixedCharge = 1000.0;
                break;
            case "ip2":
                bill = unit1 * 30.5 + (unit2 != null ? unit2 * 37.0 : 0.0) + (unit3 != null ? unit3 * 25.5 : 0.0);
                fixedCharge = 5000.0;
                demandCharge = 1500.0;
                break;
            case "ip3":
                bill = unit1 * 30.0 + (unit2 != null ? unit2 * 36.0 : 0.0) + (unit3 != null ? unit3 * 24.5 : 0.0);
                fixedCharge = 5000.0;
                demandCharge = 1400.0;
                break;
            case "h1-1":
                if (totalUnits <= 300) {
                    bill = totalUnits * 13.5;
                    fixedCharge = 300.0;
                } else {
                    bill = 300 * 13.5 + (totalUnits - 300) * 21.5;
                    fixedCharge = 1000.0;
                }
                break;
            case "h1-2":
                bill = totalUnits * 21.5;
                fixedCharge = 1000.0;
                break;
            case "h2":
                bill = unit1 * 30.5 + (unit2 != null ? unit2 * 37.0 : 0.0) + (unit3 != null ? unit3 * 25.5 : 0.0);
                fixedCharge = 5000.0;
                demandCharge = 1500.0;
                break;
            case "h3":
                bill = unit1 * 30.0 + (unit2 != null ? unit2 * 36.0 : 0.0) + (unit3 != null ? unit3 * 24.5 : 0.0);
                fixedCharge = 5000.0;
                demandCharge = 1400.0;
                break;
            case "gp1-1":
                if (totalUnits <= 180) {
                    bill = totalUnits * 33.0;
                    fixedCharge = 600.0;
                } else {
                    bill = 180 * 33.0 + (totalUnits - 180) * 43.0;
                    fixedCharge = 1500.0;
                }
                break;
            case "gp1-2":
                bill = totalUnits * 43.0;
                fixedCharge = 1500.0;
                break;
            case "gp2":
                bill = unit1 * 45.0 + (unit2 != null ? unit2 * 55.0 : 0.0) + (unit3 != null ? unit3 * 37.0 : 0.0);
                fixedCharge = 5000.0;
                demandCharge = 1500.0;
                break;
            case "gp3":
                bill = unit1 * 44.0 + (unit2 != null ? unit2 * 54.0 : 0.0) + (unit3 != null ? unit3 * 36.0 : 0.0);
                fixedCharge = 5000.0;
                demandCharge = 1400.0;
                break;
            case "gv1-1":
                if (totalUnits <= 180) {
                    bill = totalUnits * 33.0;
                    fixedCharge = 600.0;
                } else {
                    bill = 180 * 33.0 + (totalUnits - 180) * 43.0;
                    fixedCharge = 1500.0;
                }
                break;
            case "gv1-2":
                bill = totalUnits * 43.0;
                fixedCharge = 1500.0;
                break;
            case "gv2":
                bill = unit1 * 45.0 + (unit2 != null ? unit2 * 55.0 : 0.0) + (unit3 != null ? unit3 * 37.0 : 0.0);
                fixedCharge = 5000.0;
                demandCharge = 1500.0;
                break;
            case "gv3":
                bill = unit1 * 44.0 + (unit2 != null ? unit2 * 54.0 : 0.0) + (unit3 != null ? unit3 * 36.0 : 0.0);
                fixedCharge = 5000.0;
                demandCharge = 1400.0;
                break;
            case "streetlighting":
                bill = totalUnits * 45.0;
                break;
            case "agriculture1":
                bill = totalUnits * 33.0;
                fixedCharge = 1000.0;
                break;
            default:
                System.out.println("Invalid category specified.");
                return 0.0;
        }

        double tax = (bill + fixedCharge + demandCharge) * 0.025;

        return bill + fixedCharge + demandCharge + tax;
    }
}
