package com.backend;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.MQTTClient;

import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = {"/electricity/regional-admin/iot-control", "/water/regional-admin/iot-control"})
public class Control extends HttpServlet {
    private MQTTClient mqttClient;

    @Override
    public void init() {
        try {
            mqttClient = new MQTTClient();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize MQTT client", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String iotId = req.getParameter("iotId");
        String newStatus = req.getParameter("newStatus");
        String action = Objects.equals(newStatus, "ACTIVE") ? "Connect" : "Disconnect";

        String category = req.getServletPath().split("/")[1];
        System.out.println(category);


        try {
            if (action.equals("Connect")) {
                mqttClient.publish("Connect", iotId, category.toUpperCase());
                resp.getWriter().write("Sent 'Connect' message to Device");

            } else {

                mqttClient.publish("Disconnect", iotId, category.toUpperCase());
                resp.getWriter().write("Sent 'Disconnect' message to Device");

            }
        } catch (Exception e) {
            resp.getWriter().write("Failed to publish action");
        }
    }

    @Override
    public void destroy() {
        try {
            mqttClient.disconnect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
