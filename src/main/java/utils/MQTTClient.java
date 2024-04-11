package utils;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTClient {
    private static final String BROKER_URL = "tcp://your-mqtt-broker.com:1883";
    private static final String TOPIC = "electricity-meter/readings";

    private final MqttClient mqttClient;

    public MQTTClient() throws MqttException {
        mqttClient = new MqttClient(BROKER_URL, MqttClient.generateClientId(), new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        mqttClient.connect(options);
        mqttClient.subscribe(TOPIC);
    }

    public void setCallback(MqttCallback callback) throws MqttException {
        mqttClient.setCallback(callback);
    }

    public void disconnect() throws MqttException {
        mqttClient.disconnect();
    }
}
