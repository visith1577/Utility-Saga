package utils;

import DAO.dao.ReadingSaver;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class ElectricityMeterCallback implements MqttCallback {
    private final AtomicReference<String> latestReading;



    public ElectricityMeterCallback(AtomicReference<String> latestReading) {
        this.latestReading = latestReading;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost: " + throwable.getMessage());
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) {
        String payload = new String(mqttMessage.getPayload());
        // Process the electricity meter reading payload
        System.out.println("Received electricity meter reading: " + payload);
        processReading(payload); // Call a method to process the reading
        if (mqttMessage.isRetained()) {
            System.out.println("messages retained");
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // Handle delivery complete
        System.out.println("Delivery complete for message: " + iMqttDeliveryToken.getMessageId());
    }

    private void processReading(String payload) {
        try {
            getScheduledReading(payload);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Processing reading: " + payload);
        // ...
    }


    private void getScheduledReading(String load) throws SQLException {
        latestReading.set(load);
        Runnable readingSaverTask = new ReadingSaver(latestReading);
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(readingSaverTask, 0, 30, TimeUnit.SECONDS);
    }
}
