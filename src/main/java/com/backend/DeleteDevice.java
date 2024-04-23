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

@WebServlet(name = "DeleteDevice", urlPatterns = {"/electricity/regional-admin/api/delete-device", "/water/regional-admin/api/delete-device"})
public class DeleteDevice extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deviceId = req.getParameter("deviceId");
        String accountNumber = req.getParameter("accountNo");

        Device dao = new IotControl();
        ElecWaterAccountsModelImpl model = new ElecWaterAccountsDAO();

        try {

            if (Objects.equals(req.getServletPath(), "/water/api/update-device")) {
                dao.deleteDeviceId(accountNumber, deviceId, "WATER");
                resp.getWriter().write("Device updated successfully");
                return;
            } else if (Objects.equals(req.getServletPath(), "/electricity/api/update-device")) {
                dao.deleteDeviceId(accountNumber, deviceId, "ELECTRICITY");
                resp.getWriter().write("Device updated successfully");
                return;
            }

            if (!Objects.equals(deviceId, "NO")) {
                model.deleteMeterTable(deviceId);
                model.deleteMeterBudgetTable(deviceId);
            }

            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Error deleting device");
        }
    }
}
