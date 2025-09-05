package org.wowauctionhouse.dto.commodities;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.wowauctionhouse.dto.common.Links;
import java.util.List;

public record CommoditiesResponse(
        @JsonProperty("_links") Links links,
        List<CommodityAuction> auctions
) {}
