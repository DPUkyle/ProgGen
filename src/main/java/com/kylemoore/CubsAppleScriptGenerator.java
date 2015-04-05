package com.kylemoore;

import java.time.format.DateTimeFormatter;

public class CubsAppleScriptGenerator implements IAppleScriptGenerator {

    @Override
    public String generateAppleScript(IProgram program) {
        StringBuilder sb = new StringBuilder();

        sb.append(program.debugString()).append('\n');

        String opponentString = " Cubs " + (program.getOpponent().contains("@") ? "at" + program.getOpponent().replace('@', ' ') : program.getOpponent());
        String titleString = "MLB Baseball:".concat(opponentString);

        //if the games are not numbered, instead prefix the date
        String episodePrefix = program.getOrdinal() > 0 ? Integer.toString(program.getOrdinal())
                                                        : program.getDate().format(DateTimeFormatter.ISO_DATE);

        String episodeString = episodePrefix.concat(opponentString);

        String dateString = program.getDate().format(IAppleScriptGenerator.getDefaultFormatter());

        sb.append("make new program with properties {title:\"").append(titleString)
                .append("\", start time:date \"").append(dateString)
                .append("\", channel number:\"").append(program.getChannel().getChannelNumber())
                .append("\", duration:").append(12600) //3.5 hours
                .append(", episode:\"").append(episodeString)
                .append("\"}");

        sb.append('\n');

        return sb.toString();
    }

}
