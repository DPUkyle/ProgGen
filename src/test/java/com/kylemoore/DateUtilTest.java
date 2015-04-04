package com.kylemoore;

import org.junit.Test;

import java.time.ZonedDateTime;

import static org.fest.assertions.api.Assertions.assertThat;

public class DateUtilTest {

    @Test
    public void mergeStrings() {

        ZonedDateTime result = DateUtil.joinDateAndTime("Saturday, April 18", "1:20 PM");
        assertThat(result.toString()).isEqualTo("2015-04-18T13:20-05:00[America/Chicago]");

        ZonedDateTime resultPlusOneHour = DateUtil.asEasternTime(result);

        assertThat(resultPlusOneHour.toString()).isEqualTo("2015-04-18T14:20-04:00[America/Indiana/Indianapolis]");

        assertThat(resultPlusOneHour.toLocalDateTime().toString()).isEqualTo("2015-04-18T14:20");

    }

}
