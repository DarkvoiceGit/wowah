package org.wowauctionhouse.blizzard;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.wowauctionhouse.dto.auction.AuctionsResponse;
import org.wowauctionhouse.dto.commodities.CommoditiesResponse;
import reactor.core.publisher.Mono;
import org.wowauctionhouse.config.BlizzardProperties;

@Service
public class WowApiClient {

    private final WebClient http;
    private final BlizzardTokenService tokens;
    private final BlizzardProperties props;

    public WowApiClient(WebClient.Builder builder, BlizzardTokenService tokens, BlizzardProperties props) {
        this.http = builder.baseUrl(props.apiBaseUrl()).build();
        this.tokens = tokens;
        this.props = props;
    }

    /** Beispiel: Alle Auktionen für einen Connected-Realm holen */
    public Mono<AuctionsResponse> getAuctions(long connectedRealmId) {
        String namespace = "dynamic-" + props.region();
        return tokens.getAccessToken()
                .flatMap(token -> http.get()
                        .uri(uri -> uri
                                .path("/data/wow/connected-realm/{id}/auctions")
                                .queryParam("namespace", namespace)
                                .queryParam("locale", props.locale())
                                .build(connectedRealmId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(AuctionsResponse.class));
    }

    /** Optional: Regionweite Commodities (große Datei) */
    public Mono<CommoditiesResponse> getCommodities() {
        String namespace = "dynamic-" + props.region();
        return tokens.getAccessToken()
                .flatMap(token -> http.get()
                        .uri(uri -> uri
                                .path("/data/wow/auctions/commodities")
                                .queryParam("namespace", namespace)
                                .queryParam("locale", props.locale())
                                .build())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(CommoditiesResponse.class));
    }
}
