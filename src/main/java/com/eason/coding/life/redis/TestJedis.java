package com.eason.coding.life.redis;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Transaction;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJedis {
	
    private static final int SET_COUNT = 100000;
	private static Jedis jedis;
    private static ShardedJedis sharding;
    private static ShardedJedisPool pool;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        List<JedisShardInfo> shards = Arrays.asList(
                new JedisShardInfo("localhost",6380),
                new JedisShardInfo("localhost",6381)); //use the same instance for test purpose


        jedis = new Jedis("localhost"); 
        sharding = new ShardedJedis(shards);

        pool = new ShardedJedisPool(new JedisPoolConfig(), shards);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        jedis.disconnect();
        sharding.disconnect();
        pool.destroy();
    }

    @Test
    public void test1Normal() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < SET_COUNT; i++) {
            String result = jedis.set("n" + i, "n" + i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Simple SET: " + ((end - start)/1000.0) + " seconds");
    }

    @Test
    public void test2Trans() {
        long start = System.currentTimeMillis();
        Transaction tx = jedis.multi();
        for (int i = 0; i < SET_COUNT; i++) {
            tx.set("t" + i, "t" + i);
        }
        //System.out.println(tx.get("t1000").get());

        List<Object> results = tx.exec();
        long end = System.currentTimeMillis();
        System.out.println("Transaction SET: " + ((end - start)/1000.0) + " seconds");
    }

    @Test
    public void test3Pipelined() {
        Pipeline pipeline = jedis.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0; i < SET_COUNT; i++) {
            pipeline.set("p" + i, "p" + i);
        }
        //System.out.println(pipeline.get("p1000").get());
        List<Object> results = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("Pipelined SET: " + ((end - start)/1000.0) + " seconds");
    }

    @Test
    public void test4combPipelineTrans() {
        long start = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        pipeline.multi();
        for (int i = 0; i < SET_COUNT; i++) {
            pipeline.set("" + i, "" + i);
        }
        pipeline.exec();
        List<Object> results = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("Pipelined transaction: " + ((end - start)/1000.0) + " seconds");
    }

    @Test
    public void test5shardNormal() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < SET_COUNT; i++) {
            String result = sharding.set("sn" + i, "sn" + i);
        }
        System.out.println(sharding.get("sn1"));
        long end = System.currentTimeMillis();
        System.out.println("Simple@Sharing SET: " + ((end - start)/1000.0) + " seconds");
    }

    @Test
    public void test6shardpipelined() {
        ShardedJedisPipeline pipeline = sharding.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0; i < SET_COUNT; i++) {
            pipeline.set("sp" + i, "p" + i);
        }
        List<Object> results = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("Pipelined@Sharing SET: " + ((end - start)/1000.0) + " seconds");
    }

    @Test
    public void test7shardSimplePool() {
        ShardedJedis one = pool.getResource();

        long start = System.currentTimeMillis();
        for (int i = 0; i < SET_COUNT; i++) {
            String result = one.set("spn" + i, "n" + i);
        }
        long end = System.currentTimeMillis();
        pool.returnResource(one);
        System.out.println("Simple@Pool SET: " + ((end - start)/1000.0) + " seconds");
    }

    @Test
    public void test8shardPipelinedPool() {
        ShardedJedis one = pool.getResource();

        ShardedJedisPipeline pipeline = one.pipelined();

        long start = System.currentTimeMillis();
        for (int i = 0; i < SET_COUNT; i++) {
            pipeline.set("sppn" + i, "n" + i);
        }
        List<Object> results = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        pool.returnResource(one);
        System.out.println("Pipelined@Pool SET: " + ((end - start)/1000.0) + " seconds");
    }
}
