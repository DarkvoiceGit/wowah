package org.wowauctionhouse.dto.commodities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.wowauctionhouse.dto.common.ItemRef;
import org.wowauctionhouse.dto.common.TimeLeft;

public record CommodityAuction(
        long id,
        ItemRef item,
        int quantity,
        @JsonProperty("unit_price") long unitPrice,
        @JsonProperty("time_left") TimeLeft timeLeft
) {}
