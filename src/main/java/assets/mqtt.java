package assets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.paho.client.mqttv3.MqttException;
import utils.ElectricityMeterCallback;
import utils.MQTTClient;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet("/getdata")
public class mqtt extends HttpServlet {

    private MQTTClient mqttClient;
    private final AtomicReference<String> latestReading = new AtomicReference<>();

    @Override
    public void init() throws ServletException {
        try {
            mqttClient = new MQTTClient();
            mqttClient.setCallback(new ElectricityMeterCallback(latestReading));
        } catch (MqttException e) {
            throw new ServletException("Failed to initialize MQTT client", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reading = latestReading.get();
        if (reading != null) {
            resp.getWriter().write("Latest reading: " + reading);
        } else {
            resp.getWriter().write("No data received yet");
        }
    }

    @Override
    public void destroy() {
        try {
            mqttClient.disconnect();
        } catch (MqttException e) {
            System.out.println(e.getMessage());
        }
    }
}
