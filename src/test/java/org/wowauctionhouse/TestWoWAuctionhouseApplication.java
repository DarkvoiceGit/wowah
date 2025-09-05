package org.wowauctionhouse;

import org.springframework.boot.SpringApplication;

public class TestWoWAuctionhouseApplication {

    public static void main(String[] args) {
        SpringApplication.from(WoWAuctionhouseApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
