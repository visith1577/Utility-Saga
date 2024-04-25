package com.backend;

import DAO.dao.ElecWaterAccountsDAO;
import DAO.dao.IotControl;
import DAO.impl.Device;
import DAO.impl.ElecWaterAccountsModelImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "DeleteDevice", urlPatterns = {"/electricity/regional-admin/api/delete-device", "/water/regional-admin/api/delete-device"})
public class DeleteDevice extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deviceId = req.getParameter("deviceId");
        String accountNumber = req.getParameter("accountNo");

        Device dao = new IotControl();

        try {

            if (Objects.equals(req.getServletPath(), "/water/regional-admin/api/delete-device")) {
                dao.deleteDeviceId(accountNumber, deviceId, "WATER");

            } else if (Objects.equals(req.getServletPath(), "/electricity/regional-admin/api/delete-device")) {
                dao.deleteDeviceId(accountNumber, deviceId, "ELECTRICITY");

            }

            resp.sendRedirect(req.getHeader("referer") + "?success=true");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getHeader("referer"));
        }
    }
}
