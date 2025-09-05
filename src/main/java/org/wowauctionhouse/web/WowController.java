package org.wowauctionhouse.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.wowauctionhouse.dto.auction.AuctionsResponse;
import org.wowauctionhouse.dto.commodities.CommoditiesResponse;
import reactor.core.publisher.Mono;
import org.wowauctionhouse.blizzard.WowApiClient;

@RestController
public class WowController {

    private final WowApiClient client;

    public WowController(WowApiClient client) {
        this.client = client;
    }

    @GetMapping("/auctions")
    public Mono<AuctionsResponse> auctions(@RequestParam long connectedRealmId) {
        return client.getAuctions(connectedRealmId);
    }

    @GetMapping("/commodities")
    public Mono<CommoditiesResponse> commodities() {
        return client.getCommodities();
    }
}
