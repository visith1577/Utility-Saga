package assets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.MQTTClient;

import java.io.IOException;

@WebServlet("/control")
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
        String action = req.getParameter("action");
        if (action != null) {
            try {
                if(action.equals("Connect")) {

                    mqttClient.publish("Connect");
                    resp.getWriter().write("Sent 'Connect' message to simulator");

                } else if(action.equals("Disconnect")) {

                    mqttClient.publish("Disconnect");
                    resp.getWriter().write("Sent 'disconnect' message to simulator");

                } else {

                    resp.getWriter().write("Invalid action. Use 'connect' or 'disconnect'.");

                }
            } catch (Exception e) {
                resp.getWriter().write("Failed to publish action: " + action);
            }
        } else {
            resp.getWriter().write("No action specified");
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
