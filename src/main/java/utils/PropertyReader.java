package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private final static Logger logger = LogManager.getLogger(PropertyReader.class);

    private final String resourceName;
    private Properties properties;

    public PropertyReader(final String resourceName){
        this.resourceName = resourceName;
        logger.info("Resource Name :: "+resourceName);
    }

    private void load() throws IOException {
        logger.info("Resource is being loaded..!");
        properties = new Properties();
        properties.load(Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(resourceName));
    }
    public Object get(final String key) throws IOException{
        logger.info("Resource key :: "+key);
        if (properties == null){
            load();
        }
        logger.info("Resource value :: "+properties.get(key));
        return properties.get(key);
    }
}
