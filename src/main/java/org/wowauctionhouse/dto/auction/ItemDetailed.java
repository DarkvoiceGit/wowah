package org.wowauctionhouse.dto.auction;

import java.util.List;

public record ItemDetailed(
        long id,
        Integer context,
        List<Integer> bonusLists,
        List<Modifier> modifiers
) {}