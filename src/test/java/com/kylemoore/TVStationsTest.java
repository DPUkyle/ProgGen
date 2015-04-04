package com.kylemoore;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class TVStationsTest {

    @Test
    public void assertTheOldNumberNineExists() {
        assertThat(TVStations.WGN.getChannelNumber()).isEqualTo(1180);
    }
}
