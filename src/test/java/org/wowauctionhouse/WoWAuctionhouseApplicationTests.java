package org.wowauctionhouse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class WoWAuctionhouseApplicationTests {

    @Test
    void contextLoads() {
    }

}
