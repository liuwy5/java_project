package util.redis;

import redis.clients.jedis.*;
import util.configuration.ConfigUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ubuntu.
 */
public class RedisClient {
    private JedisPool jedisPool; //非切片连接池
    private ShardedJedisPool shardedJedisPool; //切片连接池
    private static RedisClient redisClient;

    private static String host = ConfigUtil.readRedisProperties().getString("redis.ip");
    private static int port = ConfigUtil.readRedisProperties().getInt("redis.port");

    private RedisClient(){
        initialPool();
        initialShardedPool();
    }

    public static RedisClient getRedisClient(){
        if(redisClient == null){
            synchronized (RedisClient.class){
                if(redisClient == null)
                    redisClient = new RedisClient();
            }
        }
        return redisClient;
    }

    public Jedis getJedis(){
        return jedisPool.getResource();
    }

    public ShardedJedis getShardedJedis(){
        return shardedJedisPool.getResource();
    }

    /**
     * 初始化非切片池
     */
    private void initialPool(){
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(ConfigUtil.readRedisProperties().getInt("redis.pool.maxTotle"));
        config.setMaxIdle(ConfigUtil.readRedisProperties().getInt("redis.pool.maxIdle"));
        config.setMaxWaitMillis(ConfigUtil.readRedisProperties().getLong("redis.pool.maxWaitMillis"));
        config.setTestOnBorrow(ConfigUtil.readRedisProperties().getBoolean("redis.pool.testOnBorrow"));

        jedisPool = new JedisPool(config, host, port);
    }

    /**
     * 初始化切片池
     */
    private void initialShardedPool(){
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(ConfigUtil.readRedisProperties().getInt("redis.pool.maxTotle"));
        config.setMaxIdle(ConfigUtil.readRedisProperties().getInt("redis.pool.maxIdle"));
        config.setMaxWaitMillis(ConfigUtil.readRedisProperties().getLong("redis.pool.maxWaitMillis"));
        config.setTestOnBorrow(ConfigUtil.readRedisProperties().getBoolean("redis.pool.testOnBorrow"));

        // slave链接
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo(host, port, "master"));

        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

    public void Close(){
        jedisPool.close();
        shardedJedisPool.close();
    }
}
