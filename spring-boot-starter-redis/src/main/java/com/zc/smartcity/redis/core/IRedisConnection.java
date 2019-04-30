package com.zc.smartcity.redis.core;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.Jedis;

/**
 * <p>
 *      IRedisConnection
 * </p>
 *
 * @author: hejianhui
 * @see IRedisConnection
 * @since JDK1.8
 */
public interface IRedisConnection extends InitializingBean, DisposableBean {

    Jedis getJedis();

    String getBusiness();
}
