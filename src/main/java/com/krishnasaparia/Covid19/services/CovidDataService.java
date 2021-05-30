package com.krishnasaparia.Covid19.services;

import com.krishnasaparia.Covid19.models.LocationState;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class CovidDataService {
    private static final String DataURL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<LocationState> allStats = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* * * 1 * * ")
    public void fetchCovidData() throws IOException, InterruptedException {
        List<LocationState> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DataURL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            LocationState locationstate = new LocationState();
            locationstate.setState(record.get("Province/State"));
            locationstate.setCountry(record.get("Country/Region"));
            locationstate.setLatestCases(Integer.parseInt(record.get(record.size() - 1)));
            System.out.println(locationstate);
            newStats.add(locationstate);
        }
        this.allStats = newStats;

    }
}
