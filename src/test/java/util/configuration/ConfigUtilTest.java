package util.configuration;

import org.apache.commons.configuration.Configuration;
import org.junit.Test;

/**
 * Created by ubuntu.
 */
public class ConfigUtilTest {
    @Test
    public void readRedisPropertiesTest(){
        Configuration configuration = ConfigUtil.readRedisProperties();
        System.out.println(configuration.getString("redis.ip"));
        System.out.println(configuration.getInt("redis.port"));
    }
}
