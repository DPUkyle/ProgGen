package com.kylemoore;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

public class HTMLReaderTest {

    private static File _file = null;
    private static IReader _htmlReader = null;

    @BeforeClass
    public static void beforeClass() {
        _file = new File("src/test/resources/Test_Schedule.html");
        _htmlReader = new HTMLReader(new File("src/test/resources/Test_Schedule.html"));
    }

    @Test
    public void getAllData() {
        ISchedule schedule = _htmlReader.read();
        schedule.getPrograms().stream().forEach( program -> System.out.println(program.debugString()));
    }

}
