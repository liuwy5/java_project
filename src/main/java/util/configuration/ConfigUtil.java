package util.configuration;


import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by ubuntu.
 */
public class ConfigUtil {
    private static Configuration configuration = null;

    public static Configuration readRedisProperties(){
        try{
            configuration = new PropertiesConfiguration("redis.properties");
        } catch (Exception e){
            e.printStackTrace();
        }
        return configuration;
    }
}
