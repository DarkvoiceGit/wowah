package org.wowauctionhouse.dto.auction;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.wowauctionhouse.dto.common.TimeLeft;

public record Auction(
        long id,
        ItemDetailed  item,
        Long bid,          // optional im JSON -> Wrapper
        Long buyout,       // optional
        int quantity,
        @JsonProperty("time_left") TimeLeft timeLeft
) {}