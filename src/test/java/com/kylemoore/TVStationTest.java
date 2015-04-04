package com.kylemoore;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class TVStationTest {

    @Test
    public void assertTheOldNumberNineExists() {
        assertThat(TVStation.WGN.getChannelNumber()).isEqualTo(1180);
//        fail("Failing on purpose!");
    }
}
