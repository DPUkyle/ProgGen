package com.kylemoore;

import java.time.LocalDateTime;

public class Program implements IProgram {

    private int _ordinal;
    private LocalDateTime _date;
    private String _opponent;
    private TVStation _channel;

    private Program() {} //prevent instantiation

    public static Program init(int ordinal, LocalDateTime date, String opponent, TVStation channel) {
        Program program = new Program();
        program._ordinal = ordinal;
        program._date = date;
        program._opponent = opponent;
        program._channel = channel;
        return program;
    }

    @Override
    public int getOrdinal() {
        return _ordinal;
    }

    @Override
    public LocalDateTime getDate() {
        return _date;
    }

    @Override
    public String getOpponent() {
        return _opponent;
    }

    @Override
    public TVStation getChannel() {
        return _channel;
    }

}