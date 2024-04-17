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


    public int billCalculator(int units) {
        return 0;
    }
}
