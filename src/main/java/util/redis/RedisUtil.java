package util.redis;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;
import util.configuration.ConfigUtil;

import java.util.Set;

/**
 * Created by ubuntu.
 */
public class RedisUtil {
    private static String host = ConfigUtil.readRedisProperties().getString("redis.ip");
    private static int port = ConfigUtil.readRedisProperties().getInt("redis.port");
    static Jedis jedis = new Jedis(host, port);

    public static String T(){
        jedis.set("a", "value");
        return jedis.get("a");
    }

    public static void transaction(){
        Transaction transaction = jedis.multi();

        transaction.set("key1", "value1");
        Response<String> value = transaction.get("key1");

        transaction.zadd("key2", 1, "value11");
        transaction.zadd("key2", 1, "value12");
        transaction.zadd("key2", 1, "value13");
        Response<Set<String>> soso = transaction.zrange("key2", 0, -1);

        // 1>
        // a Response Object does not contain the result before t.exec() is called
        transaction.exec();

        String result1 = value.get();  // use Response.get() to retrieve things from a Response
        int result2 = soso.get().size();
        System.out.println(result1);
        System.out.println(result2);

        // 2>
        // could still get all results at once, as before
        // but need to extract objects from a list, which contains also Redis status messages
//        List<Object> list = transaction.exec();
//        System.out.println(list.size());
    }

    /**
     * Sometimes you need to send a bunch of different commands.
     * This way you send commands without waiting for response, and you actually read the responses at the end, which is faster.
     */
    public static void popelining(){
        Pipeline pipeline = jedis.pipelined();

        pipeline.set("key1", "value1");
        Response<String> value = pipeline.get("key1");

        pipeline.zadd("key2", 1, "value11");
        pipeline.zadd("key2", 1, "value12");
        pipeline.zadd("key2", 1, "value13");
        Response<Set<String>> soso = pipeline.zrange("key2", 0, -1);

        pipeline.sync();

        String result1 = value.get();  // use get() to retrieve things from a Response
        int result2 = soso.get().size();
        System.out.println(result1);
        System.out.println(result2);
    }
}
