package io.dbychkowsky.globaltempstats.services;

import io.dbychkowsky.globaltempstats.models.TempStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class GlobalStatsDataService {
    //TODO: Find regularly updated temperature datasets
    private static final String TEMP_DATA_URL =
            "https://pkgstore.datahub.io/core/global-temp/monthly_csv/data/"
                    + "c1321100952fc1b643ec604ae65a104a/monthly_csv.csv";
    private List<TempStats> tempStats = new ArrayList<>();

    public List<TempStats> getTempStats() {
        return tempStats;
    }

    @PostConstruct
    @Scheduled(cron = "0 0 * * * *")
    public void fetchTempData() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(TEMP_DATA_URL))
                .build();
        HttpResponse<String> httpResponse =
                httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        StringReader csvReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
        tempStats.clear();
        for (CSVRecord record : records) {
            TempStats tempStat = new TempStats();
            tempStat.setDataSource(record.get("Source"));
            tempStat.setRecordedDate(LocalDate.parse(record.get("Date"), DateTimeFormatter.ISO_DATE));
            tempStat.setMeanTemp(Double.parseDouble(record.get("Mean")));
            tempStats.add(tempStat);
        }
    }
}
