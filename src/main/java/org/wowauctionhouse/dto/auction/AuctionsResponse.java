package org.wowauctionhouse.dto.auction;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.wowauctionhouse.dto.common.Links;


import java.util.List;

public record AuctionsResponse(
        @JsonProperty("_links") Links links,
        @JsonProperty("connected_realm") ConnectedRealmLink connectedRealm,
        List<Auction> auctions
) {}