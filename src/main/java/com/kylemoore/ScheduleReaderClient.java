package com.kylemoore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class ScheduleReaderClient {

    //private static final Logger _logger = LoggerFactory.getLogger(ScheduleReaderClient.class);

    private IReader _reader = null;

    public ScheduleReaderClient(IReader reader) {
        _reader = reader;
    }

    private ISchedule fetchData() {
        return _reader.read();
    }

    public static void main(String... args) {
        try {
            URL url = new URL(args[0]);
            IReader reader = new HTMLReader(url);
            ScheduleReaderClient client = new ScheduleReaderClient(reader);

            IAppleScriptGenerator generator = new CubsAppleScriptGenerator();

            client.fetchData().getPrograms().forEach(p ->
                            System.out.println(generator.generateAppleScript(p))
            );
        } catch (MalformedURLException e) {
            throw new Error(e.toString());
        }
    }

}
