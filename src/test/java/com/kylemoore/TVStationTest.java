package com.kylemoore;

import org.junit.Test;

import java.util.stream.Stream;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class TVStationTest {

    @Test
    public void assertTheOldNumberNineExists() {
        assertThat(TVStation.WGN.getChannelNumber()).isEqualTo(1180);
//        fail("Failing on purpose!");
    }

    @Test
    public void stringToEnum() {
        // This list should not return any "unknown" values from the toEnum function
        Stream<String> inputs = Stream.of(
                "ABC 7",
                "ABC 7, FS1",
                "CSN",
                "CSN+",
                "CSN, ESPN2",
                "ESPN2",
                "FOX",
                "WGN",
                "WPWR");

        inputs.forEach(value -> assertThat(toEnum(value)).isNotEqualTo(TVStation.UNKNOWN));
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
