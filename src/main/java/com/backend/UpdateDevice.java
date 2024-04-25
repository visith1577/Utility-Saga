package com.backend;

import DAO.dao.ElecWaterAccountsDAO;
import DAO.dao.IotControl;
import DAO.impl.Device;
import DAO.impl.ElecWaterAccountsModelImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@WebServlet(name = "UpdateDevice", urlPatterns = {"/electricity/regional-admin/api/update-device", "/water/regional-admin/api/update-device"})
public class UpdateDevice extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deviceId = req.getParameter("deviceId");
        String accountNumber = req.getParameter("accountNo");
        String prevDeviceId = req.getParameter("prevDeviceId");

        Device dao = new IotControl();

        try {

            if (Objects.equals(req.getServletPath(), "/water/regional-admin/api/update-device")) {
                dao.updateDeviceId(accountNumber, deviceId, prevDeviceId, "WATER");

            } else if (Objects.equals(req.getServletPath(), "/electricity/regional-admin/api/update-device")) {
                dao.updateDeviceId(accountNumber, deviceId,prevDeviceId, "ELECTRICITY");

            }

            resp.sendRedirect(req.getHeader("referer") + "?success=true");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getHeader("referer"));
        }
    }
}
