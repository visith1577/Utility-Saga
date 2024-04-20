package utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.net.ssl.SSLSocketFactory;
import java.util.Objects;


public class MQTTClient {

    public static final Dotenv dotenv = Dotenv
            .configure()
            .load();
    private static final String BROKER_URL = "ssl://" +  dotenv.get("HIVE_URL") + ":" + dotenv.get("HIVE_PORT");
    private static final String TOPIC = dotenv.get("TOPIC");
    private static final String CONTROL_TOPIC = dotenv.get("CNTR_TOPIC");
    private final MqttClient mqttClient;

    public MQTTClient() throws MqttException {
        mqttClient = new MqttClient(BROKER_URL, MqttClient.generateClientId(), new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(dotenv.get("HIVE_USER"));
        options.setPassword(Objects.requireNonNull(dotenv.get("HIVE_PASS")).toCharArray());
        // using the default socket factory
        options.setSocketFactory(SSLSocketFactory.getDefault());
        options.setCleanSession(false);
        mqttClient.connect(options);
        mqttClient.subscribe(TOPIC, 2, null);
    }

    public void setCallback(MqttCallback callback) throws MqttException {
        mqttClient.setCallback(callback);
    }

    public void publish(String message) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttClient.publish(CONTROL_TOPIC, mqttMessage.getPayload(), 2, true);
    }

    public void disconnect() throws MqttException {
        mqttClient.disconnect();
    }
}
