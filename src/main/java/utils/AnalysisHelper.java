package utils;

import DAO.dao.AnalyticDao;
import model.IoTModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnalysisHelper {

    public List<IoTModel> getDifferenceBetweenReadings(String account) throws SQLException {
        AnalyticDao analyticDao = new AnalyticDao();
        List<IoTModel> data = analyticDao.getElectricityMeterDataMonthly(account);
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
        boolean isTimeOfUse = (unit2 > 0 || unit3 > 0); // Check if Time of Use Electricity Tariff is used

        if (isTimeOfUse) {
            // Calculate bill using Time of Use Electricity Tariff
            bill += unit1 * 70.0; // Day Time
            bill += unit2 * 90.0; // Peak Time
            bill += unit3 * 30.0; // Off-Peak Time
            fixedCharge = 2000.0;
        } else {
            // Calculate bill using the default method
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

    public static double calculateReligiousCharitableBill(double units) {
        double bill = 0.0;
        double fixedCharge = 0.0;

        if (units <= 30) {
            bill = units * 8.0;
            fixedCharge = 150.0;
        } else if (units <= 90) {
            bill = 30 * 8.0 + (units - 30) * 9.0;
            fixedCharge = 250.0;
        } else if (units <= 120) {
            bill = 30 * 8.0 + 60 * 9.0 + (units - 90) * 18.0;
            fixedCharge = 600.0;
        } else if (units <= 180) {
            bill = 30 * 8.0 + 60 * 9.0 + 30 * 18.0 + (units - 120) * 32.0;
            fixedCharge = 1500.0;
        } else {
            bill = 30 * 8.0 + 60 * 9.0 + 30 * 18.0 + 60 * 32.0 + (units - 180) * 43.0;
            fixedCharge = 2000.0;
        }

        double amt =  bill + fixedCharge;
        double tax = amt * 0.025;

        amt += tax;
        return amt;
    }
}
