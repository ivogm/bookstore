package com.ivohasablog.bookstore.cache;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import com.ivohasablog.bookstore.cache.serializable.ShoppingCartDSFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by Ivo on 18.11.2017 г..
 */
@Configuration
public class HazelcastConfig {

    /** Helper constants */
    private static final String HZ_MEMBERS_SEPARATOR = ",";

    /** Hazelcast Maps */
    public static final String OWL_MAP="outcomeWagerLimitMap";
    public static final String SHOPPING_CART_MAP ="shoppingCartMap";

    @Autowired
    private HazelcastProperties hzProperties;

    @Bean
    public CacheManager cacheManager() {
        return new HazelcastCacheManager(hazelcastInstance());
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();

        //Cluster
        String clusterName = hzProperties.getGroup().getName();
        config.setInstanceName(clusterName);
        config.getGroupConfig().setName(clusterName);
        config.getGroupConfig().setPassword(hzProperties.getGroup().getPassword());

        //Properties
        config.getSerializationConfig().addDataSerializableFactory(1, new ShoppingCartDSFactory());
        config.setProperty("hazelcast.logging.type", hzProperties.getLogging().getType());
        config.setProperty("hazelcast.jmx", hzProperties.getJmx());

        //Network
        NetworkConfig network = config.getNetworkConfig();
        network.setPort(hzProperties.getNetwork().getPort());
        network.setPortAutoIncrement(hzProperties.getNetwork().isPortAutoIncrement());
        JoinConfig join = network.getJoin();
        join.getMulticastConfig().setEnabled(false);
        TcpIpConfig tcpIpConfig = join.getTcpIpConfig();
        tcpIpConfig.setEnabled(true);
        tcpIpConfig.setMembers(Arrays.asList(hzProperties.getCluster().getMembers().split(HZ_MEMBERS_SEPARATOR)));

        //Maps
        config.addMapConfig(configShoppingCartMap());

        return Hazelcast.newHazelcastInstance(config);
    }

    private MapConfig configShoppingCartMap() {
        MapConfig awlMapConfig = new MapConfig();
        awlMapConfig.setName(SHOPPING_CART_MAP);
        awlMapConfig.setEvictionPolicy(EvictionPolicy.LFU);
        awlMapConfig.addMapIndexConfig(new MapIndexConfig("id", true));
        return awlMapConfig;
    }
}
