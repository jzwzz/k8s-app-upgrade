package com.jzwzz.k8sdemo.k8sappupgrade.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 36000)
public class RedisSessionConfig {

    @Bean
    public static ConfigureRedisAction configureRedisAction() {

        return ConfigureRedisAction.NO_OP;
    }

}