package org.wowauctionhouse.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "blizzard")
public record BlizzardProperties(
        String region,
        String apiBaseUrl,
        String oauthTokenUrl,
        String clientId,
        String clientSecret,
        String locale
) {}