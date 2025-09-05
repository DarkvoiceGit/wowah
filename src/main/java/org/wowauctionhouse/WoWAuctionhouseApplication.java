package org.wowauctionhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.wowauctionhouse.config.BlizzardProperties;

@SpringBootApplication
@EnableConfigurationProperties(BlizzardProperties.class)
public class WoWAuctionhouseApplication {
    public static void main(String[] args) {
        SpringApplication.run(WoWAuctionhouseApplication.class, args);
    }
}
