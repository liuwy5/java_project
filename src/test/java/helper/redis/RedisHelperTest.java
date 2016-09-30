package helper.redis;

import org.junit.Test;

/**
 * Created by ubuntu.
 */
public class RedisHelperTest {
    @Test
    public void TTest(){
        String value = RedisHelper.T();
        System.out.println(value);
    }

    @Test
    public void transactionTest(){
        RedisHelper.transaction();
    }

    @Test
    public void popeliningTest(){
        RedisHelper.popelining();
    }
}
