package com.kylemoore;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HTMLReader implements IReader {

    private final Logger _logger = LoggerFactory.getLogger(HTMLReader.class);

    private Document _doc = null;
    private ISchedule _schedule = null;
    private List<TVStation> _blacklist = Arrays.asList(TVStation.ABC7, TVStation.WCIU, TVStation.WPWR, TVStation.UNKNOWN);

    public HTMLReader(URL url) {
        try {
            Connection connection = Jsoup.connect(url.toString());
            _doc = connection.get();
        } catch (java.io.IOException e) {
            _logger.error(e.toString());
        }
    }

    public HTMLReader(File file) {
        try {
            _doc = Jsoup.parse(file, StandardCharsets.UTF_8.toString());
        } catch (java.io.IOException e) {
            _logger.error(e.toString());
        }
    }

    @Override
    public ISchedule read() {
        _schedule = new Schedule();

        Elements tableElements = _doc.select("table[id=broadcast_schedule]");

        Elements headers = tableElements.select("th");

        Map<String, Integer> headerMap = asColumnMap(headers);

        Elements rows = tableElements.select("tr:not(:has(th))"); // get all tr elements which do not contain a th element

        //filter time TBD
        rows.stream()
                .filter( row -> !row.child(headerMap.get("Time")).text().equalsIgnoreCase("TBD"))
                .filter(row -> !_blacklist.contains(toEnum(row.child(headerMap.get("TV")).text())))
                .forEach(row -> {
                    ZonedDateTime localStartTime = DateUtil.joinDateAndTime(row.child(headerMap.get("Date")).text(), row.child(headerMap.get("Time")).text());
                    LocalDateTime adjustedStartTime = DateUtil.asEasternTime(localStartTime).toLocalDateTime();
                    String opponent = row.child(headerMap.get("Opponent")).text();
                    TVStation channel = toEnum(row.child(headerMap.get("TV")).text());

                    _schedule.addProgram(-1, adjustedStartTime, opponent, channel);
                });

        return _schedule;
    }

    private Map<String, Integer> asColumnMap(Elements elements) {
        return elements.stream()
                .collect(Collectors.toMap(Element::text,
                                          Element::elementSiblingIndex));
    }

    private TVStation toEnum(String value) {
        if(value == null || value.isEmpty()) {
            return TVStation.UNKNOWN;
        }

        switch(value) {
            case "ABC 7":
                return TVStation.ABC7;
            case "ABC 7, FS1":
                return TVStation.FS1;
            case "CSN, ESPN2":
                return TVStation.CSN;
            case "CSN+":
                return TVStation.CSNPLUS;
            //the following elements map 1:1 to the Enum
            case "CSN":
            case "ESPN":
            case "ESPN2":
            case "FOX":
            case "WCIU":
            case "WGN":
            case "WPWR":
                return TVStation.valueOf(value);
            default:
                System.out.println("Warning, unknown station: " + value);
                return TVStation.UNKNOWN;
        }
    }
}
