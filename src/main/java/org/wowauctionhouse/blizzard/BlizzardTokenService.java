package org.wowauctionhouse.blizzard;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.wowauctionhouse.config.BlizzardProperties;

@Service
public class BlizzardTokenService {

    private final WebClient http;
    private final BlizzardProperties props;

    private final AtomicReference<String> cachedToken = new AtomicReference<>();
    private volatile Instant expiresAt = Instant.EPOCH;

    public BlizzardTokenService(WebClient.Builder builder, BlizzardProperties props) {
        this.http = builder.build();
        this.props = props;
    }

    public Mono<String> getAccessToken() {
        // Einfaches Caching, damit wir nicht bei jedem Request ein neues Token holen
        if (Instant.now().isBefore(expiresAt.minus(24, ChronoUnit.HOURS))) {
            return Mono.just(cachedToken.get());
        }

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "client_credentials");

        return http.post()
                .uri(props.oauthTokenUrl())
                .headers(h -> h.setBasicAuth(props.clientId(), props.clientSecret()))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(form)
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .map(tr -> {
                    cachedToken.set(tr.access_token);
                    // expires_in ist in Sekunden
                    expiresAt = Instant.now().plusSeconds(tr.expires_in);
                    return tr.access_token;
                });
    }

    // interne DTO-Klasse fürs Token
    static class TokenResponse {
        public String access_token;
        public long expires_in;
        public String token_type;
        public String scope;
    }
}
