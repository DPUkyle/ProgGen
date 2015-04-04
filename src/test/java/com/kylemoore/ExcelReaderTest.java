package com.kylemoore;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.Month;

import static org.fest.assertions.api.Assertions.assertThat;

public class ExcelReaderTest {

    private static File _excelFile = null;
    private static IReader _excelReader = null;

    @BeforeClass
    public static void beforeClass() {
        _excelFile = new File("src/test/resources/Test_Schedule.xls");
    }

    @Test
    public void fileExists() {
        assertThat(_excelFile.exists()).as("No file at " + _excelFile.getAbsolutePath()).isTrue();
    }

    @Test
    public void openFile() {
        try {
            _excelReader = new ExcelReader(_excelFile);
        } catch (java.io.FileNotFoundException e) {
            //unhandled
        } finally {
            assertThat(_excelReader).isNotNull();
        }
    }

    @Test
    public void getScheduleAndPrograms() {
        ISchedule schedule = _excelReader.read();
        assertThat(schedule).isNotNull();
        assertThat(schedule.getPrograms()).isNotEmpty();

        IProgram firstProgram = schedule.getPrograms().get(0);
        assertThat(firstProgram.getOrdinal()).isEqualTo(11);
        assertThat(firstProgram.getDate()).isEqualTo(LocalDateTime.of(2015, Month.APRIL, 18, 14, 20));
        assertThat(firstProgram.getOpponent()).isEqualTo("vs. San Diego");
        assertThat(firstProgram.getChannel()).isEqualTo(TVStation.CSN);
    }

}
