package com.kylemoore;

import java.io.File;
import java.io.FileNotFoundException;

public class ScheduleReaderClient {

    private IReader _reader = null;

    public ScheduleReaderClient(IReader reader) {
        _reader = reader;
    }

    private ISchedule fetchData() {
        return _reader.read();
    }

    public static void main(String... args) {
        String filename = args[0];
        File excelFile = new File(filename);
        System.out.println("excelFile is : " + excelFile.getAbsolutePath());
        try {
            IReader excelReader = new ExcelReader(excelFile);
            ScheduleReaderClient client = new ScheduleReaderClient(excelReader);

            IAppleScriptGenerator generator = new CubsAppleScriptGenerator();

            client.fetchData().getPrograms().forEach(p ->
                            System.out.println(generator.generateAppleScript(p))
            );
        } catch (java.io.FileNotFoundException e) {
            throw new Error(e.toString());
        }
    }

}
