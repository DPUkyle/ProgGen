package com.kylemoore;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class AppleScriptGeneratorTest { //implements IAppleScriptGenerator {

    private static ISchedule _schedule = null;

    @BeforeClass
    public static void beforeClass() {
        try {
            //_schedule = new ExcelReader(new File("src/test/resources/Test_Schedule.xls")).read();
            _schedule = new HTMLReader(new File("src/test/resources/Test_Schedule.html")).read();
        } catch(Exception e) {

        }
        assertThat(_schedule).isNotNull();
    }

    @Test
    public void verifyAppleScript() {

        //define the implementation of IASG#generateAppleScript
        IAppleScriptGenerator generator = new CubsAppleScriptGenerator();

//        _schedule.getPrograms().forEach(generator::generateAppleScript); //This is called a method reference
        _schedule.getPrograms().forEach( p ->
            System.out.println(generator.generateAppleScript(p))
        );
    }
}
