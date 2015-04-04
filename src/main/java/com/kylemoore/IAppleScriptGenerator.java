package com.kylemoore;

import java.time.format.DateTimeFormatter;

public interface IAppleScriptGenerator {

    String generateAppleScript(IProgram program);

    /**
     * @return The default applescript datetime format
     */
    static DateTimeFormatter getDefaultFormatter() {
        return DateTimeFormatter.ofPattern("EEEE, MMMM dd, uuuu HH:mm");
    }


}
