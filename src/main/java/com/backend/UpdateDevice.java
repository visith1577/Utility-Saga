package com.backend;

import DAO.dao.ElecWaterAccountsDAO;
import DAO.dao.IotControl;
import DAO.impl.Device;
import DAO.impl.ElecWaterAccountsModelImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;


@WebServlet(name = "UpdateDevice", urlPatterns = {"/electricity/regional-admin/api/update-device", "/water/regional-admin/api/update-device"})
public class UpdateDevice extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deviceId = req.getParameter("deviceId");
        String accountNumber = req.getParameter("accountNo");

        // Update the device with the given deviceId and accountNumber
        Device dao = new IotControl();
        ElecWaterAccountsModelImpl model = new ElecWaterAccountsDAO();

        try {

            if (Objects.equals(req.getServletPath(), "/water/api/update-device")) {
                dao.updateDeviceId(deviceId, accountNumber, "WATER");
                resp.getWriter().write("Device updated successfully");
                return;
            } else if (Objects.equals(req.getServletPath(), "/electricity/api/update-device")) {
                dao.updateDeviceId(deviceId, accountNumber, "ELECTRICITY");
                resp.getWriter().write("Device updated successfully");
                return;
            }

            model.createMeterTable(deviceId);
            model.createMeterBudgetTable(deviceId);
            model.insertInitialBudget(deviceId);

            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Error updating device");
        }
    }
}
