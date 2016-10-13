package util.redis;

import org.junit.Test;

/**
 * Created by ubuntu.
 */
public class RedisUtilTest {
    @Test
    public void TTest(){
        String value = RedisUtil.T();
        System.out.println(value);
    }

    @Test
    public void transactionTest(){
        RedisUtil.transaction();
    }

    @Test
    public void popeliningTest(){
        RedisUtil.popelining();
    }
}
