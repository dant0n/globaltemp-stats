package io.dbychkowsky.globaltempstats.services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GlobalStatsDataService {
    //TODO: Find recent temperature datasets
    private static final String TEMP_DATA_URL =
            "https://pkgstore.datahub.io/core/global-temp/monthly_csv/data/"
                    + "c1321100952fc1b643ec604ae65a104a/monthly_csv.csv";

    @PostConstruct
    public void fetchTempData() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(TEMP_DATA_URL))
                .build();
        HttpResponse<String> httpResponse =
                httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());
    }
}
