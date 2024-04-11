package utils;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ElectricityMeterCallback implements MqttCallback {
    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost: " + throwable.getMessage());
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String payload = new String(mqttMessage.getPayload());
        // Process the electricity meter reading payload
        System.out.println("Received electricity meter reading: " + payload);
        processReading(payload); // Call a method to process the reading
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // Handle delivery complete
        System.out.println("Delivery complete for message: " + iMqttDeliveryToken.getMessageId());
    }

    private void processReading(String payload) {
        // Implement your logic to process the electricity meter reading here
        // For example, you can save it to a database or perform calculations
        System.out.println("Processing reading: " + payload);
        // ...
    }
}
